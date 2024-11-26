//package Server;
//
///*
//Singleton
//Manages bookings and tickets
//*/
//
//import Inventory.Service;
//import Inventory.SystemInventory;
//import Ticketing.Booking;
//
//import java.util.HashMap;
//import java.util.Map;
//
//class ReservationSystem {
//    private static ReservationSystem reservationSystemSingleton = null;
//    private Map<String, Booking> bookings = new HashMap<>();
//
//
//    private ReservationSystem()
//    {
//        this.bookings = new HashMap<>();
//    }
//
//    // Static method to create instance of ReservationSystem
//    public static ReservationSystem reservationSystemSingleton()
//    {
//        // To ensure only one instance is created
//        if (reservationSystemSingleton == null) {
//            reservationSystemSingleton = new ReservationSystem();
//        }
//        return reservationSystemSingleton;
//    }
//
//    // Check if a seat is available for a given booking
//    public boolean isSeatAvailableByService(Booking booking) {
//        SystemInventory systemInventory = SystemInventory.getInstance();
//        Service service = systemInventory.getServiceById(booking.getServiceId());
//        if (service != null) {
//            if(service.isSeatAvailable()
//
//        }
//
//       // return train.isSeatAvailable(booking.getSeatNumber());
//
//    }
//
//    public Response makeReservation(Booking[] bookings) {
//        if (bookings == null || bookings.length==0) {
//            return new SimpleResponse(404, "Booking is empty");
//        }
//
//        for(int i=0; i<bookings.length; i++){
//            if()
//        }
//        if(){
//            // check booking is valid
//            // make reservations and
//            // save booking
//            // return tickets
//            return new SimpleResponse(200, tickts);
//        } else {
//            return new SimpleResponse(404, "Seats are already taken");
//        }
//    }
//}
