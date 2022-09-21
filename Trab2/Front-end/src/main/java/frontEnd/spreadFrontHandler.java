package frontEnd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spread.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.ceil;

public class spreadFrontHandler implements AdvancedMessageListener {

    private final SpreadConnection connection;
    private final SpreadGroup groupFrontEnd;
    //private static SpreadGroup[] groupMembers;
    private List<SpreadGroup> groupMembers;
    private List<Evento> eventList;
    private List<Evento> eventListAux;
    private int consumers;

    private int EVENTS_PER_MESSAGE = 5;

    private Boolean updating = false;
    private String updatingUser="";
    private int nEventosToSend=0;
    private int nMessagesSent=0;
    private Boolean start = true;

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
        if (message.startsWith("Update")){ //Update-User-nEventosToSend-nMessagesSent-Eventos[EVENTS_PER_MESSAGE]
            getUpdate(message,parts);
        } else if (message.startsWith("Consumer")) {
            consumers = Integer.parseInt(parts[1]);
            System.out.println(message);
        } else {
            Gson js = new GsonBuilder().create();
            Evento evento = js.fromJson(message,Evento.class);
            if (updating && updatingUser.equals(connection.getPrivateGroup())) eventListAux.add(evento); // Se o user que está a receber updates receber uma nova mensagem guarda num array auxiliar para não perder ordem de eventos
            else eventList.add(evento);
            System.out.println("Event size: " + eventList.size());
        }
    }

    @Override
    public void membershipMessageReceived(SpreadMessage spreadMessage) {
        MembershipInfo membershipInfo = spreadMessage.getMembershipInfo();
        try {
            if (membershipInfo.isCausedByDisconnect() && groupMembers.contains(membershipInfo.getLeft())){
                groupMembers.remove(membershipInfo.getLeft());
            }
            if (membershipInfo.isCausedByJoin() && start) {
                groupMembers = new ArrayList<>(Arrays.asList(membershipInfo.getMembers()));
                start=false;

                System.out.println("ok - Leader queue: " + groupMembers);
                System.out.println("ok - Leader queue size: " + groupMembers.size());
            }

            if (groupMembers.size()==1){
                sendUpdates(membershipInfo);
            }
            System.out.println(groupMembers);
        }catch (Exception e ){
            System.out.println(e);
        }
    }
    private void sendUpdates(MembershipInfo membershipInfo) throws Exception{
        if (nEventosToSend == 0) { //Se nEventosToSend for 0 significa que acabou de se juntar um novo Front-end
            nEventosToSend = eventList.size();
            System.out.println("Events to send: " + nEventosToSend);
        }
        if(eventList.size()>0) {
            System.out.println("---------------UPDATING---------------");
            updatingUser = updatingUser.length()==0?membershipInfo.getJoined().toString():updatingUser;
            System.out.println("Updating user: " + updatingUser);
            int n_msg = (int) ceil(nEventosToSend / EVENTS_PER_MESSAGE) - nMessagesSent;
            System.out.println("Total messages to send: "+ (n_msg+1));
            for (int i = 0; i <= n_msg; i++) {
                System.out.println("Remaining messages: "+ (n_msg-i));
                nMessagesSent++;
                int start = (nMessagesSent-1) * EVENTS_PER_MESSAGE;
                int end = nEventosToSend - nMessagesSent * EVENTS_PER_MESSAGE >= 0 ? (nMessagesSent-1) * EVENTS_PER_MESSAGE + EVENTS_PER_MESSAGE - 1 : nEventosToSend%EVENTS_PER_MESSAGE + start-1;
                StringBuilder eventsInMessage = new StringBuilder("Update-" + updatingUser + "-" + nEventosToSend + "-" + nMessagesSent + "-");
                for (int j = start; j <= end; j++) {
                    Evento ev = eventList.get(j);
                    Gson js = new GsonBuilder().create();
                    eventsInMessage.append(js.toJson(ev));
                    if (j != end) eventsInMessage.append(";");
                }
                SpreadMessage msg2 = new SpreadMessage();
                msg2.setSafe();
                msg2.addGroup(groupFrontEnd);
                msg2.setData(eventsInMessage.toString().getBytes());
                connection.multicast(msg2);
                Thread.sleep(5000);
            }
        }
    }

    private void getUpdate(String message,String[] parts){
        updating=Integer.parseInt(parts[2])>= Integer.parseInt(parts[3])*EVENTS_PER_MESSAGE; //Updating= True se ainda faltam enviar mensagens
        updatingUser=parts[1]; //Todos guardam qual o user que está a ser updated
        nEventosToSend=Integer.parseInt(parts[2]); //Todos guardam o numero de eventos a enviar
        nMessagesSent=Integer.parseInt(parts[3]); //Todos guardam o numero de mensagens já enviadas
        if(updatingUser.equals(connection.getPrivateGroup().toString())){ //Apenas o user que acabou de entrar é que atualiza os eventos
            System.out.println(message);
            String[] events = parts[4].split(";");
            eventList.addAll(Arrays.stream(events).map(ev->{
                Gson js = new GsonBuilder().create();
                return js.fromJson(ev,Evento.class);
            }).collect(Collectors.toList()));
            System.out.println("Event size: " + eventList.size());
            System.out.println("Updating: " + updating);
        }
        if(!updating){
            if(updatingUser.equals(connection.getPrivateGroup()) && eventListAux.size()>0)eventList.addAll(eventListAux);
            nEventosToSend=0;
            nMessagesSent=0;
            updatingUser="";
        }

    }
    public List<Evento> getEvents(){return eventList;}
    public int getConsumers(){return consumers;}

}