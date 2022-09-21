package rmqconfigurator;

import com.rabbitmq.client.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RabbitConfigurator {


    static String IP_BROKER="34.71.165.179";

    static Connection connection = null;
    static Channel channel = null;
    static FileWriter myWriter = null;

    public static void main(String[] args) {
        try {
            myWriter = new FileWriter("Log.txt",true);

            Scanner scan = new Scanner(System.in);

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(IP_BROKER);
            factory.setPort(5672);

            connection = factory.newConnection();
            channel = connection.createChannel();
            
            // Consumer handler to receive messages
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String recMessage = new String(delivery.getBody(), "UTF-8");
                String routingKey=delivery.getEnvelope().getRoutingKey();
                System.out.println("Message Received:" +consumerTag+":"+ routingKey+":"+recMessage);
                myWriter.write(recMessage + "\n");
                myWriter.flush();
            };
            // Consumer handler to receive cancel receiving messages
            CancelCallback cancelCallback=(consumerTag)->{
                System.out.println("CANCEL Received! "+consumerTag);
            };
            //String basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback) throws IOException;
            String consumeTag=channel.basicConsume("logger", true, deliverCallback, cancelCallback);
            System.out.println("Consumer Tag:"+consumeTag);

            System.out.println("waiting for messages or Press any key to finish");
            scan.nextLine();

            myWriter.close();
            channel.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
