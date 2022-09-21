package mqproducer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Producer {


    private static String IP_BROKER="104.197.111.218";

    private static String exchangeName = null;
    private static String routingKey = null;

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(IP_BROKER); factory.setPort(5672);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Send message to exchange
            exchangeName = "nivel2";
            routingKey = "key";

            do {
                //Creating new Events
                for (int i = 0; i < 20 ; i++){
                    Random r = new Random();
                    int low = 100;
                    int high = 150;
                    int speed = r.nextInt(high - low) + low;
                    Date date = new Date();
                    Evento evento = new Evento(1,date,"lisboa", speed);

                    Gson js = new GsonBuilder().create();
                    String jsonString = js.toJson(evento);
                    System.out.println(jsonString);

                    channel.basicPublish(exchangeName, routingKey, null, jsonString.getBytes());
                    Thread.sleep(200);
                }
            } while (readline("repeat? (y/n)").equals("y"));
            channel.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String readline(String msg) {
        Scanner scaninput = new Scanner(System.in);
        System.out.println(msg);
        return scaninput.nextLine();
    }

}
