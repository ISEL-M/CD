package ringManager;

import cdTP1.*;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerToServer extends ManagerToServiceGrpc.ManagerToServiceImplBase {
    private final ConcurrentHashMap<Integer, Service> ServersList;
    private static int nServer=3;
    private int idx=0;

    public ManagerToServer(int nServer, ConcurrentHashMap<Integer, Service> serversList){
        ServersList = serversList;
        ManagerToServer.nServer = nServer;
    }

    @Override
    public void registerService(Service server, StreamObserver<Service> responseObserver) {
        synchronized (ServersList) {
            if (!ServersList.contains(server)) {
                ServersList.put(idx, server);
                idx++;
                System.out.println("Client " + server.getIp() + ":" + server.getPort() + " Connected");

            } else {
                System.out.println("Client " + server.getIp() + ":" + server.getPort() + " already taken");
                Throwable t = new StatusException(
                        Status.INVALID_ARGUMENT.withDescription("Client nickname already taken")
                );
                responseObserver.onError(t);
                return;
            }


            if (ServersList.size() == nServer) {
                ServersList.notifyAll();
            } else {
                try {
                    ServersList.wait();
                } catch (Exception E) {
                    System.out.println(E);
                }
            }
            List<Service> serverlist = Collections.list(ServersList.elements());
            int idxa = serverlist.indexOf(server) + 1;
            if (idxa >= nServer) {
                idxa = 0;
            }

            responseObserver.onNext(serverlist.get(idxa));
            responseObserver.onCompleted();
        }
    }
}
