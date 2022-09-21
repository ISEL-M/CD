package chatserver;

import cdTP1.Service;
import cdTP1.ServiceToClient.*;
import cdTP1.ServiceToService.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;


public class ServiceToClient extends ServiceToClientGrpc.ServiceToClientImplBase {

    private Common data;

    public ServiceToClient() {
    }

    public void addCommon(Common data){
        this.data=data;
    }
    @Override
    public void sendMessage(ChatMessage inMessage, StreamObserver<Empty> responseObserver) {
        String msg=inMessage.getTxtMsg();
        ChatClientUser sender=inMessage.getFromUser();
        System.out.println("Client Called");

        if (msg.equals("exit")){
            data.removeClient(sender);
            return;
        }
        data.sendMessage(inMessage);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();

        Service service = data.getCurrService();
        ServiceChatMessage csm = ServiceChatMessage.newBuilder()
                .setFromUser(sender.getChatClient())
                .setFromServer(ServiceToS.newBuilder().setIp(service.getIp()).setPort(service.getPort()).build())
                .setTxtMsg(inMessage.getTxtMsg())
                .setFirst(true)
                .build();

        data.sendToNextObserver(csm);
    }

    @Override
    public void register(ChatClientUser clientID, StreamObserver<ChatMessage> responseObserver) {
        data.register( clientID, responseObserver);
    }

    @Override
    public void getUsers(Empty request, StreamObserver<UserIDList> responseObserver) {
        List<ChatClientUser> keys = new ArrayList<>(data.getClients().keySet());
        UserIDList users=UserIDList.newBuilder().addAllUsers(keys).build();
        responseObserver.onNext(users);
        responseObserver.onCompleted();
    }
}
