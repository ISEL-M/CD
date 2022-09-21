package frontEnd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import spread.*;

import java.net.InetAddress;
import java.util.Scanner;

public class FrontEnd {

    static String IP_BROKER="104.197.111.218";

    private static spreadFrontHandler spreadFrontHandler;

    private static String daemonIP=IP_BROKER;
    private static int daemonPort=4803;

    private static SpreadGroup FrontEndGroup;

    private static SpreadConnection connectionSpread;

    private static int port=5001;

    private static String user="user"+port;

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            // Consumer handler to receive messages
            spreadConnection(user, daemonIP, daemonPort);

            joinSpreadGroup(FrontEndGroup,"FrontEndGroup");

            final Server svc = ServerBuilder.forPort(port)
                    .addService(new frontEndInterface(spreadFrontHandler))
                    .build()
                    .start();

            System.out.println("waiting for messages or Press any key to finish");
            scan.nextLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private static void joinSpreadGroup(SpreadGroup group,String GROUP_NAME) {
        try {
            group.join(connectionSpread, GROUP_NAME);
        } catch (SpreadException e) {
            System.out.println("Couldn't join group.");
            System.out.println(e);
        }
    }



    public static void spreadConnection(String user, String address, int port) {
        try {
            FrontEndGroup = new SpreadGroup();
            connectionSpread = new SpreadConnection();
            spreadFrontHandler=new spreadFrontHandler(connectionSpread,FrontEndGroup);
            connectionSpread.add(spreadFrontHandler);
            connectionSpread.connect(InetAddress.getByName(address), port, user, false, true);
        } catch (Exception e) {
            System.err.println("There was an error connecting to the daemon.");
            e.printStackTrace();
            System.exit(1);
        }
    }

}
