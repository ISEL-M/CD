package chatclient;
import cdTP1.ServiceToClient.ChatMessage;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class EmptyObserver implements StreamObserver<Empty> {
    @Override
    public void onNext(Empty empty) {

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(" Client Empty onError!!\n"+throwable);
    }

    @Override
    public void onCompleted() {
            System.out.println("Client Empty onComplete");
        }
}

