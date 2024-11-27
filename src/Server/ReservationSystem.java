package Server;

/*
Singleton
Manages bookings and tickets
*/

import Inventory.Service;
import Inventory.SystemInventory;
import Ticketing.Booking;
import Ticketing.Ticket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReservationSystem {
    private static ReservationSystem reservationSystemSingleton = null;
    private final Map<Long, Booking> bookings;
    SystemInventory systemInventory;

    private ReservationSystem()
    {
        this.bookings = new HashMap<>();
        this.systemInventory =  SystemInventory.getInstance();
    }

    // Static method to create instance of ReservationSystem
    public static ReservationSystem getInstance()
    {
        // To ensure only one instance is created
        if (reservationSystemSingleton == null) {
            reservationSystemSingleton = new ReservationSystem();
        }
        return reservationSystemSingleton;
    }

    public Booking getBookingById(Long id){
        return bookings.get(id);
    }

    // Check if a seat is available for a given booking
    private boolean isSeatAvailable(Booking booking) throws Exception {
        Service service = systemInventory.getServiceById(booking.getServiceId());
        return (service.isSeatAvailable(booking.getCarriage(),
                booking.getSeatNumber(), booking.getSeatType(),
                        booking.getOrigin(), booking.getDestination()));
    }

    // Check if all seats are available for a few booking
    private boolean areAllSeatsAvailable(Booking[] bookings) throws Exception {
        for (Booking booking : bookings) {
            if (!isSeatAvailable(booking)) {
                return false;
            }
        }
        return true;
    }

    // Reserve a seat given booking
    private void reserveSeat(Booking booking) throws Exception {
        Service service = systemInventory.getServiceById(booking.getServiceId());
                service.reserveSeat(booking.getCarriage(),
                        booking.getSeatNumber(),
                        booking.getOrigin(),
                        booking.getDestination());
    }

    // Save all seats for a few bookings
    private void reserveSeats(Booking[] bookings) throws Exception {
        for (Booking booking : bookings) {
            reserveSeat(booking);
        }
    }

    private void saveBookings(Booking[] bookings) {
        for (Booking booking : bookings) {
            this.bookings.put(booking.getId(), booking);
        }
    }

    private Set<Ticket> createRickets(Booking[] bookings) {
        Set<Ticket> tickets = new HashSet<>();
        for (Booking booking : bookings) {
            tickets.add(
                    new Ticket(booking.getPassenger(), booking.getServiceId(),
                            booking.getOrigin(), booking.getDestination(),
                            booking.getCarriage(), booking.getSeatNumber()
                    ));
        }
        return  tickets;
    }

    // Main function to make reservation
    // Receives bookings: array of booking requests
    // Checks if all seats requests are valid and free
    // On success - saves seats and returns matching tickets for each seat
    // On failure returns error code
    public Response makeReservation(Booking[] bookings) {
        if (bookings == null || bookings.length==0) {
            return new SimpleResponse(404, "Booking is empty");
        }

     try {
         // Check if all requested seats are available
         if(!areAllSeatsAvailable(bookings)) {
             return new SimpleResponse(409, "Reservation Failed: Some seats are already taken");
         }
         // Reserve seats and create tickets
         reserveSeats(bookings);
         saveBookings(bookings);
         Set<Ticket> tickets = createRickets(bookings);

         return new SimpleResponse(200, tickets);
     }
     catch (Exception e) {
         return new SimpleResponse(500, "Internal Server Error: " + e.getMessage());
     }

    }
}
