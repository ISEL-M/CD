package chatserver;

import cdTP1.Service;
import cdTP1.ServiceToClient.ChatClientUser;
import cdTP1.ServiceToClient.ChatMessage;
import cdTP1.ServiceToService.ServiceChatMessage;
import cdTP1.ServiceToService.ServiceToServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Common {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());
    private final ConcurrentHashMap<ChatClientUser, StreamObserver<ChatMessage>> clients = new ConcurrentHashMap<>();
    private final Service service;
    private final Service nextService;
    private StreamObserver<ServiceChatMessage> sendToNextObserver;

    public Common(Service service, Service nextService) {
        logger.info("common creating");
        this.service=service;
        this.nextService=nextService;

        ManagedChannel channelToNext = ManagedChannelBuilder.forAddress(nextService.getIp(), nextService.getPort()).usePlaintext().build();
        ServiceToServiceGrpc.ServiceToServiceStub nonBlockingStub = ServiceToServiceGrpc.newStub(channelToNext);
        sendToNextObserver =  nonBlockingStub.sendMessage(new EmptyObserver());
        System.out.println("next service: " + nextService.getIp() + nextService.getPort());

    }

    public void register(ChatClientUser clientID, StreamObserver<ChatMessage> responseObserver) {
        System.out.println(clientID);
        synchronized (clients) {
            if (!clients.containsKey(clientID)) {
                clients.put(clientID, responseObserver);
                ChatMessage msg=ChatMessage.newBuilder().setTxtMsg("User Created").build();
                responseObserver.onNext(msg);
            }
            else {
                System.out.println("Client " + clientID.getChatClient() + " already taken");
                Throwable t = new StatusException(
                        Status.INVALID_ARGUMENT.withDescription("Client nickname already taken")
                );
                responseObserver.onError(t);
            }
        }
    }

    public ConcurrentHashMap<ChatClientUser, StreamObserver<ChatMessage>> getClients(){
        return clients;
    }

    public void removeClient(ChatClientUser client){
        synchronized (clients) {
            clients.remove(client);
        }
    }

    public void sendToNextObserver(ServiceChatMessage as) {
        System.out.println("Sending to next");
        sendToNextObserver.onNext(as);
    }

    public Service getCurrService() {
        return service;
    }

    public void sendMessage(ChatMessage inMessage) {
        //System.out.println(clients);
        for (ChatClientUser clientDest : clients.keySet()) {
            try {
                ChatMessage outMessage = ChatMessage.newBuilder()
                        .setFromUser(inMessage.getFromUser())
                        .setTxtMsg(inMessage.getTxtMsg()).build();
                clients.get(clientDest).onNext(outMessage);
            } catch (Throwable ex) {
                System.out.println(ex);
                // error calling remote client, remove client name and callback
                System.out.println("Client " + clientDest.getChatClient() + " removed");
                this.removeClient(inMessage.getFromUser());
            }
        }
    }
}
