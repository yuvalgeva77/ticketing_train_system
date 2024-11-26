package Ticketing;

import Inventory.Seat;
import Inventory.Service;

/*
A ticket contains a seat and is only valid for an origin and
destination on a certain service.
Successful reservation request will create matching tickets
 */

public class Ticket {
    Passenger passenger;
    Service service;
    Seat seat;
    int origin;
    int destination;
}
