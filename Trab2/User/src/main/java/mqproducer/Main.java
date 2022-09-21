package mqproducer;

import cdTP2.*;
import com.google.protobuf.Empty;
import io.grpc.*;

import java.util.Scanner;


public class Main {

    private static String serverIP = "localhost";
    private static int serverPort = 5003;
    private static Events it;
    public static void main(String[] args) {
        ManagedChannel channel=ManagedChannelBuilder.forAddress(serverIP, serverPort)
                .usePlaintext()
                .build();
        FrontEndToClientGrpc.FrontEndToClientBlockingStub BlockingStub = FrontEndToClientGrpc.newBlockingStub(channel);
        while (true) {
            System.out.println("Select one of the following options");
            System.out.println("1-Get Highest speed\n2-Get event between dates\n3-Get event between speeds\n4-Get event from sensor\n5-Get event from local\n6-Get number of consumers\n7-exit\n");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
                case "1":
                    System.out.println(BlockingStub.getHighestSpeed(Empty.newBuilder().build()));
                    break;
                case "2":
                    System.out.println("Type the beggining date, DD/MM/YY");
                    String[] dateStart=input.next().split("/");
                    System.out.println("Type the Ending date, DD/MM/YY\n");
                    String[] dateEnd=input.next().split("/");

                    Date datestart=Date.newBuilder().setAno(dateStart[2]).setMes(dateStart[1]).setDia(dateStart[0]).build();
                    Date dateend=Date.newBuilder().setAno(dateEnd[2]).setMes(dateEnd[1]).setDia(dateEnd[0]).build();
                    System.out.println(BlockingStub.getSpeedFromDate(Filter.newBuilder()
                            .setDateStart(datestart)
                            .setDateEnd(dateend).build()));
                    break;
                case "3":
                    System.out.println("Type the beggining speed");
                    int speedStart=Integer.parseInt(input.next());
                    System.out.println("Type the ending speed\n");
                    int speedEnd=Integer.parseInt(input.next());
                    it=BlockingStub.getSpeedFromSpeed(Filter.newBuilder()
                            .setSpeedStart(speedStart)
                            .setSpeedEnd(speedEnd).build());
                    System.out.println(it.getEvList());
                    break;
                case "4":
                    System.out.println("Type the sensor id\n");
                    int sensorID=Integer.parseInt(input.next());
                    it=BlockingStub.getSpeedID(Filter.newBuilder().
                            setId(sensorID).build());
                    System.out.println(it.getEvList());
                    break;
                case "5":
                    System.out.println("Type local you want to get\n");
                    String local=input.next();
                    it=BlockingStub.getSpeedFromLocal(Filter.newBuilder().setLocal(local).build());
                    System.out.println(it.getEvList());
                    break;
                case "6":
                    Consumers cs=BlockingStub.getConsumers(Empty.newBuilder().build());
                    System.out.println(cs.getNConsumer());
                    break;
                case "7":return;
                default:break;
            }
        }

    }

}
