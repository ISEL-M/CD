package mqconsumer;

import spread.*;

import java.util.*;

public class spreadElectionHandler implements AdvancedMessageListener {

    private final SpreadConnection connection;
    private SpreadGroup group;
    private ArrayList<SpreadGroup> groupMembers;
    private Boolean start = true;
    private int nConsumers=0;
    public spreadElectionHandler(SpreadConnection connection,
                                 SpreadGroup group){
        this.connection=connection;
        this.group=group;
    }

    @Override
    public void regularMessageReceived(SpreadMessage spreadMessage) {
        System.out.println("Regular Message (should not appear)");
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
        }

        try {
            if (groupMembers.size()==1){
                SpreadMessage msg = new SpreadMessage();
                msg.setSafe();
                msg.addGroup("FrontEndGroup");
                String msgData = "Consumers-"+ membershipInfo.getMembers().length;
                msg.setData(msgData.getBytes());
                connection.multicast(msg);
            }
        } catch (Exception e ){
            System.out.println(e);
        }
    }
}