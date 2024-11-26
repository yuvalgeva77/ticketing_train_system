package Ticketing;

import java.time.LocalDateTime;

// Reservation Request
public class Booking {
    private static long counter = 0; // Shared counter
    private final long id; // Unique ID for each booking
    private final String passenger;

    // Optional parameters
    private Long serviceId;
    private String seatType;
    private String origin;
    private String destination;
    private char carriage;
    private int seatNumber;
    private LocalDateTime departureTime;

    // Basic constructor for required fields
    public Booking(String passenger, String seatType, String origin, String destination, char carriage, int seatNumber) {
        counter++;
        this.id = counter;
        this.passenger = passenger;
        this.seatType = seatType;
        this.origin = origin;
        this.destination = destination;
        this.carriage = carriage;
        this.seatNumber = seatNumber;
    }

    // Constructor with optional parameters
    public Booking(String passenger, Long serviceId, String seatType, String origin, String destination, char carriage, int seatNumber, LocalDateTime departureTime) {
        this(passenger, seatType, origin, destination, carriage, seatNumber);
        this.serviceId = serviceId;
        this.departureTime = departureTime;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passenger='" + passenger + '\'' +
                ", serviceId=" + serviceId +
                ", seatType='" + seatType + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", carriage=" + carriage +
                ", seatNumber=" + seatNumber +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }

    public Long getServiceId() {
        return serviceId;
    }
}
