package chatserver;

import chat.ChatGrpc;
import chat.ChatMessage;
import chat.UserID;
import com.google.protobuf.Empty;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class ChatServer extends ChatGrpc.ChatImplBase {

    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    static int serverPort = 9000;

    public static void main(String[] args) {
        try {
            if (args.length == 1) serverPort = Integer.parseInt(args[0]);
            final Server svc = ServerBuilder.forPort(serverPort)
                    .addService(new ChatServer())
                    .build()
                    .start();
            logger.info("Server started, listening on " + serverPort);

            System.err.println("*** server await termination");
            svc.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final ConcurrentHashMap<UserID, StreamObserver<ChatMessage>> clients;


    public ChatServer() {
        clients = new ConcurrentHashMap<UserID, StreamObserver<ChatMessage>>();
    }

    @Override
    public void sendMessage(ChatMessage inMessage, StreamObserver<Empty> responseObserver) {
        for (UserID clientDest : clients.keySet()) {
            try {
                ChatMessage outMessage = ChatMessage.newBuilder()
                    .setFromUser(inMessage.getFromUser())
                    .setTxtMsg(inMessage.getTxtMsg()).build();
                clients.get(clientDest).onNext(outMessage);
            } catch (Throwable ex) {
                // error calling remote client, remove client name and callback
                System.out.println("Client " + clientDest.getName() + " removed");
                clients.remove(clientDest);
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void register(UserID clientID, StreamObserver<ChatMessage> responseObserver) {
        synchronized (clients) {
            if (!clients.containsKey(clientID))
                clients.put(clientID, responseObserver);
            else {
                System.out.println("Client " + clientID.getName() + " already taken");
                Throwable t = new StatusException(
                    Status.INVALID_ARGUMENT.withDescription("Client nickname already taken")
                );
                responseObserver.onError(t);
            }
        }
    }
}
