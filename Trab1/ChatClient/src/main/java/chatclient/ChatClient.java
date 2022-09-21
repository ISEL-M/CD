package chatclient;

import cdTP1.*;
import cdTP1.ServiceToClient.*;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient {

    public static final Logger logger = Logger.getLogger(ChatClient.class.getName());

    private static final String ringManagerIP = "35.232.99.141";
    private static final int ringManagerPort = 9001;

    public static void main(String[] args) throws Exception {
        //getting Service From Ring Manager
        ManagedChannel channel2 = ManagedChannelBuilder.forAddress(ringManagerIP, ringManagerPort)
                .usePlaintext()
                .build();
        ManagerToClientGrpc.ManagerToClientBlockingStub blockingStubManager =
                ManagerToClientGrpc.newBlockingStub(channel2);
        Service service = blockingStubManager.getAnyServer(Empty.newBuilder().build());
        logger.log(Level.INFO, "Conected to server: "+ service.getIp() + ":" +service.getPort());

        //Connecting to service
        ManagedChannel channel=ManagedChannelBuilder.forAddress(service.getIp(), service.getPort()+1)
                .usePlaintext()
                .build();
        ServiceToClientGrpc.ServiceToClientStub nonBlockingStub =
                ServiceToClientGrpc.newStub(channel);
        ServiceToClientGrpc.ServiceToClientBlockingStub BlockingStub =
                ServiceToClientGrpc.newBlockingStub(channel);

        try {
            Scanner input = new Scanner(System.in);
            while (true){
                System.out.print("\"getUsers\" or \"Register\" \n");
                String line = input.nextLine();
                if (line.equals("getUsers")){
                    ClientUserObserver obs=new ClientUserObserver();
                    nonBlockingStub.getUsers(Empty.newBuilder().build() ,obs);
                }
                if (line.equals("Register")) break;
            }

            ClientChatObserver obs=new ClientChatObserver();
            ChatClientUser user=null;

            System.out.println("Enter your nickName: ");
            String clientName = input.nextLine();
                // register client in remote server
            user = ChatClientUser
                    .newBuilder()
                    .setChatClient(clientName)
                    .build();
            nonBlockingStub.register(user,obs);
            // send messages
            System.out.println("Enter lines or the word \"getUsers\" or \"exit\"");
            while (true) {
                String line = input.nextLine();  //Em vez de se parar no cliente envia-se o exit para o servidor e este chama o oncomplete
                if (line.equals("getUsers")) {
                    nonBlockingStub.getUsers(Empty.newBuilder().build(), new ClientUserObserver());
                    continue;
                }
                if(obs.hasError()){
                    System.exit(0);
                }
                else
                    nonBlockingStub.sendMessage(ChatMessage.newBuilder().setFirst(true).setFromUser(user).setTxtMsg(line).build(), new EmptyObserver());
                if (line.equals("exit")) break;
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error:" + ex.getMessage());
        }
        if (channel!=null) {
            logger.log(Level.INFO, "Shutdown channel to server ");
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
