package Ticketing;
/*
Represent a single request for a ticket by the user
A collection of passenger information and seat reservations.
A booking has a unique identifier.
If all valid bookings received in a user requests - each booking will create a matching ticket returned to the user.
*/

// Reservation Request
public class Booking {
    private static long counter = 0; // Shared counter
    private final long id; // Unique ID for each booking
    private final Passenger passenger;

    // Optional parameters
    private Long serviceId;
    private String seatType;
    private String origin;
    private String destination;
    private char carriage;
    private int seatNumber;

    // Basic constructor for required fields
    public Booking(Passenger passenger, Long serviceId, String seatType, String origin, String destination, char carriage, int seatNumber) {
        counter++;
        this.id = counter;
        this.passenger = passenger;
        this.seatType = seatType;
        this.origin = origin;
        this.destination = destination;
        this.carriage = carriage;
        this.seatNumber = seatNumber;
        this.serviceId = serviceId;
    }

    public long getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public char getCarriage() {
        return carriage;
    }

    public int getSeatNumber() {
        return seatNumber;
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
                '}';
    }

    public Long getServiceId() {
        return serviceId;
    }
}
