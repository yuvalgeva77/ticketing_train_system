package Ticketing;

import Inventory.Seat;
import Inventory.Service;

/*
A ticket contains a seat and is only valid for an origin and destination on a certain service.
Successful reservation request will create matching tickets
 */

public class Ticket {
    Passenger passenger;
    private Long serviceId;
    private String origin;
    private String destination;
    private char carriage;
    private int seatNumber;

    public Ticket(Passenger passenger, Long serviceId, String origin, String destination, char carriage, int seatNumber) {
        this.passenger = passenger;
        this.serviceId = serviceId;
        this.origin = origin;
        this.destination = destination;
        this.carriage = carriage;
        this.seatNumber = seatNumber;
    }
}
