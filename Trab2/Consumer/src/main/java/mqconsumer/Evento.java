package mqconsumer;

import java.util.Date;

public class Evento {
    private final int id;
    private final String local;
    private final int value;
    private Date date;

    public Evento(int id,Date date ,String local, int value ){
        this.id=id;
        this.local=local;
        this.value=value;
        this.date=date;
    }

    public int getValue() {
        return value;
    }
}