package Server;

/*
Simulates REST calls by directly invoking methods on the reservationSystem.
*/

import Ticketing.Booking;

public class ReservationHTTPClient implements HTTPClient {
    private final ReservationSystem reservationSystem = ReservationSystem.getInstance();

    @Override
    public Response post(String url, Object body) {
        if (url.equals("/reservation")) {
            if (body instanceof Booking[] booking) {
                // Call ReservationSystem and return its response
              return  reservationSystem.makeReservation(booking);
            }
            return new SimpleResponse(400, "Invalid request body: Expected Booking[]");
        }
        return new SimpleResponse(404, "Unknown URL");
    }

    @Override
    public Response get(String url) {
        if (url.startsWith("/booking/")) {
            try {
                String bookingIdString = url.replace("/booking/", "");
                long bookingId = Long.parseLong(bookingIdString);
                Booking booking = reservationSystem.getBookingById(bookingId);

                return booking != null ? new SimpleResponse(200, booking) : new SimpleResponse(404, "Booking not found");
            } catch (NumberFormatException e) {
                return new SimpleResponse(400, "Invalid booking ID format");
            }
        }
        return new SimpleResponse(404, "Unknown URL");
    }
}
