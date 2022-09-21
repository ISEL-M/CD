package ringManager;


import cdTP1.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ConcurrentHashMap;

public class ManagerToClient extends ManagerToClientGrpc.ManagerToClientImplBase {
    private static ConcurrentHashMap<Integer, Service> services;
    private int idx=0;
    private final int nServer;
    private boolean ringComplete;

    public ManagerToClient( int nServer, ConcurrentHashMap< Integer, Service> services ){
        ManagerToClient.services =services;
        this.nServer=nServer;
    }

    @Override
    public void getAnyServer(Empty request, StreamObserver<Service> responseObserver) {
        try{
            if (!ringComplete) waitRingComplete();
        } catch (InterruptedException e) {
            System.out.println("okasd");
        }
        if(idx==nServer)
            idx=0;
        Service currService=services.get(idx);
        idx++;
        responseObserver.onNext(currService);
        responseObserver.onCompleted();
    }

    private void waitRingComplete() throws InterruptedException {
        synchronized(services){
            if (services.size()!=nServer) services.wait();
            else{
                services.notifyAll();
                ringComplete=true;
            }
        }
    }
}
