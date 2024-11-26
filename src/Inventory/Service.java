package Inventory;
import java.time.*;
import java.time.format.*;
import java.util.Arrays;

public class Service {
    private static final int TOTAL_CARRIAGES = 10;
    private static long servicesCount = 0; // Shared counter
    public long id; // Unique ID for each service
    Route rout;
    LocalDateTime departureTime;
    Carriage[] carriages;

    public Service(Route rout, LocalDateTime departureTime) {
        servicesCount++;
        this.id=servicesCount;
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

    public long getId() {
        return id;
    }

    public boolean isSeatAvailable(char carriage, int seat, String seatType, String origin, String destination) throws Exception{
        int seatNumber = seat-1;
        int carriageNumber =  Character.toUpperCase(carriage) - 'A';
        int originStationNumber = this.rout.getStationNumberByName(origin);
        int destinationStationNumber = this.rout.getStationNumberByName(destination);
        isValidCarriage(carriageNumber);
        return carriages[carriageNumber].isSeatAvailable(seatNumber,seatType,originStationNumber,destinationStationNumber);
    }

    public void saveSeat(int carriage, int seat, String origin, String destination) throws Exception {
        int seatNumber = seat-1;
        int carriageNumber =  Character.toUpperCase(carriage) - 'A';
        int originStationNumber = this.rout.getStationNumberByName(origin);
        int destinationStationNumber = this.rout.getStationNumberByName(destination);
        carriages[carriageNumber].saveSeat(seatNumber,originStationNumber,destinationStationNumber);
    }

    private void isValidCarriage(int carriageNumber) throws IllegalArgumentException {
        if (carriageNumber < 0 || carriageNumber >= this.carriages.length) {
            throw new IllegalArgumentException("Invalid carriage number");
        }
    }

    public String getDepartureTimeString() {
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
                "\nTime=" + getDepartureTimeString() +
                "\nCarriages=" + Arrays.toString(carriages) +
                "\n}";
    }

}
