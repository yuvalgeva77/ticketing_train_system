package Inventory;
import java.time.*;
import java.time.format.*;
import java.util.Arrays;

/*
A service is a physical train that operates at a specific route at a
specific time.
A service is using multiple carriages to make a physical train
 */

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
        createCarriages(rout.getRoutSize());
    }

    private void createCarriages(int routSize) {
        for (int i = 0; i < TOTAL_CARRIAGES; i++) {
            this.carriages[i]=new Carriage(routSize);
        }
    }

    public long getId() {
        return id;
    }

    private boolean checkAvailabilityOrReserveSeat(boolean shouldCheckAvailability, char carriage, int seat, String seatType, String origin, String destination) throws IllegalArgumentException {
        int seatNumber = seat-1; // Represents the index of the seat in the carriage seat array
        int carriageNumber =  Character.toUpperCase(carriage) - 'A'; //  // Represents the index of the carriage in the carriage array
        int originStationNumber = this.rout.getStationNumberByName(origin);
        int destinationStationNumber = this.rout.getStationNumberByName(destination);

        if(shouldCheckAvailability) { // Check if seat is valid and available
            isValidCarriage(carriageNumber);
            return carriages[carriageNumber].isSeatAvailable(seatNumber, seatType, originStationNumber, destinationStationNumber);
        }
        else { // Reserve Seat (preformed only after check seat availability returned true)
           return carriages[carriageNumber].reserveSeat(seatNumber,originStationNumber,destinationStationNumber);
        }
    }

    public boolean isSeatAvailable(char carriage, int seat, String seatType, String origin, String destination) throws IllegalArgumentException {
        return checkAvailabilityOrReserveSeat(true, carriage,  seat,  seatType,  origin,  destination);
    }

    public boolean reserveSeat(char carriage, int seat, String origin, String destination) throws IllegalArgumentException {
        return checkAvailabilityOrReserveSeat(false, carriage,  seat,  "",  origin,  destination);
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
