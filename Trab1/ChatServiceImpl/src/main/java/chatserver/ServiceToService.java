package chatserver;

import cdTP1.ServiceToService.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class ServiceToService  extends ServiceToServiceGrpc.ServiceToServiceImplBase{

    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    private Common data;

    public ServiceToService(){
    }

    public void addCommon(Common data){
        this.data=data;
    }
    @Override
    public MessageObserver sendMessage(StreamObserver<Empty> responseObserver) {
        System.out.println("create stream");
        logger.info("sendMessage");
        MessageObserver msgObs = new MessageObserver(data);
        return msgObs;
    }
}
