package chatclient;

import cdTP1.ServiceToClient.ChatMessage;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

public class ClientChatObserver implements StreamObserver<ChatMessage> {
    private boolean errorR=false;
    @Override
    public void onNext(ChatMessage chatMessage) {
        if(!chatMessage.getFromUser().getChatClient().isEmpty())
            System.out.println("[sender: " + chatMessage.getFromUser().getChatClient() + "] " + chatMessage.getTxtMsg());
        else
            System.out.println(chatMessage.getTxtMsg());
    }

    @Override
    public void onError(Throwable throwable) {
        errorR=true;
        System.out.println(" Client Error!!\n"+throwable);
    }

    @Override
    public void onCompleted() {
    }
    public boolean hasError(){
        return errorR;
    }
}
