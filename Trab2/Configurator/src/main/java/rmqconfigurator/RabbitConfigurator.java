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

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(IP_BROKER);
            factory.setPort(5672);

            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare("nivel2", BuiltinExchangeType.FANOUT, true);

            channel.queueDeclare("logger", true, false, false, null);
            channel.queueBind("logger","nivel2","key");

            channel.queueDeclare("consumer", true, false, false, null);
            channel.queueBind("consumer","nivel2","key");


            channel.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
