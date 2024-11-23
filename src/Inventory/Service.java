package Inventory;
import java.time.*;
import java.time.format.*;
import java.util.Arrays;

public class Service {
    private static final int TOTAL_CARRIAGES = 10;
    Route rout;
    LocalDateTime departureTime;
    Carriage[] carriages;

    public Service(Route rout, LocalDateTime departureTime) {
        this.rout = rout;
        this.departureTime = departureTime;
        this.carriages = new Carriage[TOTAL_CARRIAGES];
        createCariages(rout.getRoutSize());
    }

    private void createCariages(int routSize) {
        for (int i = 0; i < TOTAL_CARRIAGES; i++) {
            this.carriages[i]=new Carriage(routSize);
        }
    }

    public Route getRout() {
        return rout;
    }

    public String getDepartureTime() {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss a");
        String time = this.departureTime.format(formatter);
        return time;
    }

    @Override
    public String toString() {
        return "Service: {" +
                "\nRout=" + rout.toString() +
                "\nTime=" + getDepartureTime() +
                "\nCarriages=" + Arrays.toString(carriages) +
                "\n}";
    }

}
