package mqconsumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.*;
import spread.*;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Scanner;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Consumer {

    static String IP_BROKER="104.197.111.218";

    private static String daemonIP=IP_BROKER;
    private static int daemonPort=4803;

    private static SpreadGroup consumerGroup;
    private static SpreadGroup FrontEndGroup;
    private static SpreadConnection connectionSpread;
    private static SpreadConnection frontEndSpread;
    private static spreadElectionHandler spreadElectionHandler;

    private static String user="user1";

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(IP_BROKER); factory.setPort(5672);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            spreadConnection(user, daemonIP, daemonPort);
            System.out.println("User: " + user);

            Scanner scan = new Scanner(System.in);

            // Consumer handler to receive messages
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                System.out.println("--------------------------------\nNew Message! ");
                messageProcessing(delivery.getBody());
            };
            DeliverCallback deliverCallbackWithoutAck = (consumerTag, delivery) -> {
                String recMessage = new String(delivery.getBody(), "UTF-8");
                long deliverTag=delivery.getEnvelope().getDeliveryTag();
                System.out.println("--------------------------------\nNew Message! ");
                messageProcessing(delivery.getBody());

                //void basicAck(long deliveryTag, boolean multiple) throws IOException;
                //void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException;
                if (recMessage.equals("nack"))
                    channel.basicNack(deliverTag, false, true);
                else channel.basicAck(deliverTag,false);
            };
            // Consumer handler to receive cancel receiving messages
            CancelCallback cancelCallback=(consumerTag)->{
                System.out.println("CANCEL Received! "+consumerTag);
            };
            //String basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback) throws IOException;
           String consumeTag=channel.basicConsume("consumer", true, deliverCallback, cancelCallback);
           System.out.println("Consumer Tag:"+consumeTag);

           joinSpreadGroup(consumerGroup,connectionSpread,"consumerGroup");

           System.out.println("waiting for messages or Press any key to finish");
           scan.nextLine();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private static void joinSpreadGroup(SpreadGroup group,SpreadConnection connection,String GROUP_NAME) {
        try {
            group.join(connection, GROUP_NAME);
        } catch (SpreadException e) {
            System.out.println("Couldn't join group.");
            System.out.println(e);
        }
    }


    private static void sendSpreadMessage(SpreadGroup group,byte[] message) throws SpreadException {
        SpreadMessage msg = new SpreadMessage();
        msg.setSafe();
        msg.addGroup("FrontEndGroup");
        msg.setData(message);
        connectionSpread.multicast(msg);
    }

    private static void messageProcessing(byte[] recMessage) {
        try {
            String msg = new String(recMessage, "UTF-8");
            Gson js = new GsonBuilder().create();
            Evento evento = js.fromJson(msg,Evento.class);

            if (evento.getValue()>120){
                System.out.println("Value Above 120");
                sendSpreadMessage(FrontEndGroup,recMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void spreadConnection(String user, String address, int port) {
        try {
            connectionSpread = new SpreadConnection();
            consumerGroup = new SpreadGroup();

            spreadElectionHandler=new spreadElectionHandler(connectionSpread,consumerGroup);
            connectionSpread.add(spreadElectionHandler);
            connectionSpread.connect(InetAddress.getByName(address), port, user, false, true);

        } catch (Exception e) {
            System.err.println("There was an error connecting to the daemon.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
