package chatserver;

import cdTP1.ServiceToClient.ChatClientUser;
import cdTP1.ServiceToClient.ChatMessage;
import cdTP1.ServiceToService.ServiceChatMessage;
import cdTP1.ServiceToService.ServiceToS;
import io.grpc.stub.StreamObserver;

public class MessageObserver implements StreamObserver<ServiceChatMessage> {
    private final Common data;

    protected MessageObserver(Common data) {
        this.data=data;
    }


    @Override
    public void onNext(ServiceChatMessage msgFromService) {
        ServiceToS sendingService = msgFromService.getFromServer();
        System.out.println("msg " + msgFromService.getTxtMsg() + " from " + sendingService.getIp()+ ":" + sendingService.getPort());
        System.out.println("Current System: " + data.getCurrService().getIp() + data.getCurrService().getPort());
        if (!msgFromService.getFirst() && sendingService.getIp().equals(data.getCurrService().getIp()) && sendingService.getPort()==data.getCurrService().getPort()){
            System.out.println("Exited");
            return;
        }
        ChatMessage msgToClient = ChatMessage.newBuilder()
                .setTxtMsg(msgFromService.getTxtMsg())
                .setFromUser(ChatClientUser.newBuilder().setChatClient(msgFromService.getFromUser()).build())
                .setFirst(false)
                .build();

        data.sendMessage(msgToClient);

        data.sendToNextObserver(msgFromService.toBuilder().setFirst(false).build());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("ljasddddddddddddddddddddddddddddddddddddddd");
    }

    @Override
    public void onCompleted() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
    }
}
