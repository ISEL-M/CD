package frontEnd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spread.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.ceil;

public class spreadFrontHandler implements AdvancedMessageListener {

    private final SpreadConnection connection;
    private final SpreadGroup groupFrontEnd;
    private ArrayList<SpreadGroup> groupMembers;
    private Boolean start = true;


    private List<Evento> eventList;
    private List<Evento> eventListAux;
    private int consumers;

    private int EVENTS_PER_MESSAGE = 5;

    private Boolean updating = false;
    private int eventsReceived = 0;
    private int messagesReceived = 0;


    public spreadFrontHandler(SpreadConnection connection,
                              SpreadGroup groupFrontEnd){
        this.connection=connection;
        this.groupFrontEnd=groupFrontEnd;
        eventList = new ArrayList<>();
        eventListAux = new ArrayList<>();
    }

    @Override
    public void regularMessageReceived(SpreadMessage spreadMessage) {
        String message = new String(spreadMessage.getData());
        String[] parts = message.split("-");
        if (message.startsWith("Update")){
            if (!(groupMembers.size() == 1)) update(parts);
        } else if (message.startsWith("Consumer")){
            consumers = Integer.getInteger(parts[1]);

        } else {
            newMessage(message);
        }
        System.out.println("ok - event size: " + eventList.size());
    }

    @Override
    public void membershipMessageReceived(SpreadMessage spreadMessage) {
        MembershipInfo membershipInfo = spreadMessage.getMembershipInfo();

        if (membershipInfo.isCausedByDisconnect() && groupMembers.contains(membershipInfo.getLeft())){
            groupMembers.remove(membershipInfo.getLeft());
        }

        if (membershipInfo.isCausedByJoin() && start) {
            groupMembers = new ArrayList<>(Arrays.asList(membershipInfo.getMembers()));
            start=false;

            System.out.println("ok - Leader queue: " + groupMembers);
            System.out.println("ok - Leader queue size: " + groupMembers.size());
        }

        if (groupMembers.size()==1 && membershipInfo.isCausedByJoin()){
            sendUpdates();
        }
    }

    private void sendUpdates(){
        try {
            if (eventList.size()>0){
                int n_msg = (int) ceil((double) eventList.size()/EVENTS_PER_MESSAGE);
                int sent_msg = messagesReceived;
                for(int i=sent_msg; i<n_msg; i++) {
                    int startEvent = (i) * EVENTS_PER_MESSAGE;
                    int endEvent = startEvent + EVENTS_PER_MESSAGE;
                    if (endEvent>eventList.size()) endEvent = eventList.size();
                    StringBuilder eventsInMessage = new StringBuilder("Update-" + n_msg + "-" + endEvent + "-");
                    for (int j = startEvent; j < endEvent; j++) {
                        Evento ev = eventList.get(j);
                        Gson js = new GsonBuilder().create();
                        eventsInMessage.append(js.toJson(ev));
                        if (j != endEvent - 1) eventsInMessage.append(";");
                    }
                    SpreadMessage msg = new SpreadMessage();
                    msg.setSafe();
                    msg.addGroup(groupFrontEnd);
                    msg.setData(eventsInMessage.toString().getBytes());
                    connection.multicast(msg);
                    Thread.sleep(5000);
                }
            }
        }catch (Exception e ){
            System.out.println(e);
        }
    }

    private void newMessage(String ev){
        Gson js = new GsonBuilder().create();
        Evento evento = js.fromJson(ev,Evento.class);
        if (updating) eventListAux.add(evento);
        else{
            eventList.add(evento);
        }
    }

    private void update(String[] parts){
        updating=true;
        String[] events = parts[3].split(";");
        messagesReceived++;
        eventsReceived =Integer.parseInt(parts[2]);
        if (eventList.size() < eventsReceived){
            eventList.addAll(Arrays.stream(events).map(ev->{
                Gson js = new GsonBuilder().create();
                return js.fromJson(ev,Evento.class);
            }).collect(Collectors.toList()));
        }
        if (Integer.parseInt(parts[1])==messagesReceived){
            updating=false;
            messagesReceived=0;
            eventList.addAll(eventListAux);
        }
    }


    //outside functions
    public List<Evento> getEvents(){return eventList;}
    public int getConsumers(){return consumers;}

}