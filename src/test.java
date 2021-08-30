import static java.util.concurrent.TimeUnit.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class test {

    public static void main(String[] args) throws Exception {
        
        final ScheduledExecutorService servicioDeEjecucionProgramado = Executors.newScheduledThreadPool(0);

        for (int i = 0; i < 5; i++) {
            Thread user = new Thread(new Usuario("00" + i));
            ScheduledFuture<?> futuroProgramado = servicioDeEjecucionProgramado.scheduleWithFixedDelay(user, 1, 1,
                    SECONDS);
            servicioDeEjecucionProgramado.schedule(new Runnable() {
                public void run() {
                    futuroProgramado.cancel(true);
                }
            }, 10, SECONDS);

        }

    }

}
