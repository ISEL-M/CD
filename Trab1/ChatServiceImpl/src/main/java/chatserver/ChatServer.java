package chatserver;


import cdTP1.*;
import io.grpc.*;
import java.net.InetAddress;

import java.net.UnknownHostException;
import java.util.logging.Logger;

public class ChatServer {

    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    //static String serverIP="146.148.92.165";
    static String serverIP="34.70.81.193";
    //static String serverIP="localhost";
    static int serverPort = 8000;

    static String RingManagerIP="35.232.99.141";
    static int RingManagerPort=9000;
    //static int RingManagerIP = inetAdress.;

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostName());
        try {
            if(args.length == 1){
                serverPort=Integer.parseInt(args[0]);
            }

            ServiceToClient  ServerToClient= new ServiceToClient();
            final Server svc = ServerBuilder.forPort(serverPort+1)
                    .addService(ServerToClient)
                    .build()
                    .start();


            ServiceToService ServerToServer=new ServiceToService();
            //Create Service for other Services
            final Server svc2 = ServerBuilder.forPort(serverPort)
                    .addService(ServerToServer)
                    .build()
                    .start();

            //Connect to ring
            ManagedChannel channel= ManagedChannelBuilder.forAddress(RingManagerIP, RingManagerPort)
                    .usePlaintext()
                    .build();
            ManagerToServiceGrpc.ManagerToServiceBlockingStub blockingStub = ManagerToServiceGrpc.newBlockingStub(channel);


            //sharing ip and waiting for next Service from the ring
            logger.info("Waiting ring answer...");
            Service service= Service.newBuilder().setIp(serverIP).setPort(serverPort).build();
            Service nextService;
            try{
                nextService = blockingStub.registerService(service);
                System.out.println(nextService);
            }catch (Throwable t){
                System.out.println(t.getMessage());
                return;
            }

            //Create Service for Clients
            Common data = new Common(service,nextService);

            ServerToServer.addCommon(data);
            ServerToClient.addCommon(data);


            logger.info("Server started, listening on " + serverPort);
            System.err.println("*** server await termination");

            svc.awaitTermination();
            svc2.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
