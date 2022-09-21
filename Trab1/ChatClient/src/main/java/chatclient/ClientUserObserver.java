package chatclient;

import cdTP1.ServiceToClient.UserIDList;
import io.grpc.stub.StreamObserver;

public class ClientUserObserver implements StreamObserver<UserIDList> {
    @Override
    public void onNext(UserIDList chatMessage) {
        System.out.println("[Users: " + chatMessage.getUsersList() + "] \n" );
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Client Client User onError!!\n"+throwable);
    }

    @Override
    public void onCompleted() {
        System.out.println("Client Client User onComplete");
    }
}

