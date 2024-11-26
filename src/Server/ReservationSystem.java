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
    private Map<Long, Booking> bookings;
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
        return (service != null &&
                service.isSeatAvailable(booking.getCarriage(),
                booking.getSeatNumber(), booking.getSeatType(),
                        booking.getOrigin(), booking.getDestination())); //TODO
    }

    // Check if all seats are available for a few booking
    private boolean isAllSeatsAvailable(Booking[] booking) throws Exception {
        for (int i = 0; i < booking.length; i++) {
            if(!isSeatAvailable(booking[i])){
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
                        booking.getDestination()); //TODO
    }

    // Save all seats for a few bookings
    private void reserveSeats(Booking[] booking) throws Exception {
        for (int i = 0; i < booking.length; i++) {
            reserveSeat(booking[i]);
        }
    }

    private void saveBookings(Booking[] booking) {
        for (int i = 0; i < booking.length; i++) {
            this.bookings.put(booking[i].getId(), booking[i]);
        }
    }

    private Set<Ticket> createRickets(Booking[] booking) {
        Set<Ticket> tickets = new HashSet<>();
        for (int i = 0; i < booking.length; i++) {
            tickets.add(
                    new Ticket(booking[i].getPassenger(), booking[i].getServiceId(),
                    booking[i].getOrigin(), booking[i].getDestination(),
                            booking[i].getCarriage(), booking[i].getSeatNumber()
                    ));
        }
        return  tickets;
    }

    public Response makeReservation(Booking[] bookings) {

        if (bookings == null || bookings.length==0) {
            return new SimpleResponse(404, "Booking is empty");
        }

     try {
         // Check if all requested seats are available
         if(!isAllSeatsAvailable(bookings)) {
             return new SimpleResponse(409, "Reservation Failed: Some seats are already taken");
         }
         // Reserve seats and create tickets
         reserveSeats(bookings);
         saveBookings(bookings);
         Set<Ticket> tickets = createRickets(bookings);

         return new SimpleResponse(200, tickets);
     }
     catch (Exception e) {
         e.printStackTrace();
         return new SimpleResponse(500, "Internal Server Error: " + e.getMessage());
     }

    }
}
