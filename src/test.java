import java.util.concurrent.ScheduledExecutorService;

public class test {

    public static void main(String[] args) throws Exception {
        System.out.println("Hola mundo!");
        Beep beep = new Beep();
        beep.beepForAnHour();
    }

}
