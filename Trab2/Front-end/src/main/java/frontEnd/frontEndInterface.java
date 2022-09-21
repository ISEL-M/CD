package frontEnd;
import cdTP2.*;
import cdTP2.Date;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.stream.Collectors;

public class frontEndInterface extends FrontEndToClientGrpc.FrontEndToClientImplBase{

    private spreadFrontHandler spreadFrontHandler;
    public frontEndInterface(spreadFrontHandler spreadFrontHandler){
        this.spreadFrontHandler=spreadFrontHandler;
    }

    @Override
    public void getHighestSpeed(Empty request, StreamObserver<Event> responseObserver) {
        List<Evento> events=spreadFrontHandler.getEvents();
        Evento highest=events.stream().max(Comparator.comparingInt(Evento::getValue)).get();
        Event ev=Event.newBuilder()
                .setDate(getTime(highest.getDate()))
                .setId(highest.getId()).setLocal(highest.getLocal())
                .setSpeed(highest.getValue())
                .build();
        responseObserver.onNext(ev);
        responseObserver.onCompleted();
    }

    @Override
    public void getSpeedFromDate(Filter request, StreamObserver<Event> responseObserver) {
        //TODO comparar a data
//        Date dates=request.getDateStart();
//        Date datee=request.getDateEnd();
//
//        List<Evento> events=spreadFrontHandler.getEvents();
//        Evento[] evs=events.stream().filter()
    }

    @Override
    public void getConsumers(Empty request, StreamObserver<Consumers> responseObserver) {
        Consumers consumers=Consumers.newBuilder().setNConsumer(spreadFrontHandler.getConsumers()).build();
        responseObserver.onNext(consumers);
        responseObserver.onCompleted();
    }

    @Override
    public void getSpeedID(Filter request, StreamObserver<Events> responseObserver) {
        int id=request.getId();
        List<Event> events=spreadFrontHandler.getEvents().stream().filter(ev->ev.getId()==id).map(evento -> {
            Event ev=Event.newBuilder()
                    .setDate(getTime(evento.getDate()))
                    .setId(evento.getId())
                    .setLocal(evento.getLocal())
                    .setSpeed(evento.getValue())
                    .build();
            return ev;
        }).collect(Collectors.toList());

        responseObserver.onNext(Events.newBuilder().addAllEv(events).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSpeedFromLocal(Filter request, StreamObserver<Events> responseObserver) {
        String local=request.getLocal();
        List<Event> events=spreadFrontHandler.getEvents().stream().filter(ev->ev.getLocal().equals(local)).map(evento -> {
            Event ev=Event.newBuilder()
                    .setDate(getTime(evento.getDate()))
                    .setId(evento.getId())
                    .setLocal(evento.getLocal())
                    .setSpeed(evento.getValue())
                    .build();
            return ev;
        }).collect(Collectors.toList());

        responseObserver.onNext(Events.newBuilder().addAllEv(events).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSpeedFromSpeed(Filter request, StreamObserver<Events> responseObserver) {
        int speedS=request.getSpeedStart();
        int speedE=request.getSpeedEnd();

        List<Event> events=spreadFrontHandler
                .getEvents()
                .stream()
                .filter(ev->ev.getValue()>=speedS && ev.getValue()<=speedE)
                .map(evento -> {
                    Event ev=Event.newBuilder()
                    .setDate(getTime(evento.getDate()))
                    .setId(evento.getId())
                    .setLocal(evento.getLocal())
                    .setSpeed(evento.getValue()).build();
                    return ev;
        }).collect(Collectors.toList());

        responseObserver.onNext(Events.newBuilder().addAllEv(events).build());
        responseObserver.onCompleted();
    }

    private Date getTime(java.util.Date date){
        Calendar cal= new GregorianCalendar();
        cal.setTime(date);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String hora = String.valueOf(cal.get(Calendar.HOUR));
        String minuto = String.valueOf(cal.get(Calendar.MINUTE));
        String segundo = String.valueOf(cal.get(Calendar.SECOND));
        return Date.newBuilder().setAno(year).setMes(month).setDia(day).setHora(hora).setMinuto(minuto).setSegundo(segundo).build();
    }
}
