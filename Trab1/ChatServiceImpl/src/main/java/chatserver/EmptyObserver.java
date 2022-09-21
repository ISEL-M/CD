package chatserver;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class EmptyObserver implements StreamObserver<Empty> {
    @Override
    public void onNext(Empty empty) {
        System.out.println("Empty onNext");
    }

    @Override
    public void onError(Throwable throwable) {


        System.out.println(" Service Empty onError!!\n"+throwable + "\n"+throwable.getCause());
    }

    @Override
    public void onCompleted() {
        System.out.println("Service Empty onComplete");
    }
}

