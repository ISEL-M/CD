package ringManager;


import cdTP1.*;
import io.grpc.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


public class RingManager {

    private static final Logger logger = Logger.getLogger(RingManager.class.getName());
    static int serverPort = 9000;

    public static void main(String[] args) {
        int nServer=3;
        try {
            if(args.length > 0){
                nServer = Integer.parseInt(args[0]);
            }


            ConcurrentHashMap<Integer, Service> ServersList = new ConcurrentHashMap<>();
            ManagerToServer managerToServer = new ManagerToServer(nServer, ServersList);
            final Server svc = ServerBuilder.forPort(serverPort)
                    .addService(managerToServer)
                    .build()
                    .start();


            logger.info("Server started, listening on " + serverPort + "\nWaiting for Chat Servers");
            ManagerToClient managerToClient = new ManagerToClient(nServer, ServersList);
            final Server svc2 = ServerBuilder.forPort(serverPort+1)
                    .addService(managerToClient)
                    .build()
                    .start();


            System.err.println("*** server await termination");
            svc.awaitTermination();
            svc2.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
