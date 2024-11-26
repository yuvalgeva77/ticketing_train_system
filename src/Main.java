import Inventory.*;
import Server.HTTPClient;
import Server.ReservationHTTPClient;
import Server.ReservationSystem;
import Server.Response;
import Ticketing.Booking;
import Ticketing.Passenger;

import java.time.*;

public class Main {
    public static void main(String[] args) {
        SystemInventory systemInventory = SystemInventory.getInstance();
        ReservationSystem reservationSystem = ReservationSystem.getInstance();
        HTTPClient httpClient = new ReservationHTTPClient();

        Station paris1 = new Station("Paris1");
        Station paris2 = new Station("Paris2");
        Station london1 = new Station("London1");
        Station london2 = new Station("London2");
        Station amsterdam1 = new Station("Amsterdam1");
        Station amsterdam2 = new Station("Amsterdam2");
        Station berlin1 = new Station("Berlin1");
        Station berlin2 = new Station("Berlin2");

        Route parisLondoRroute = new Route(new Station[]{paris1, paris2, london1, london2}, new int[]{0,100,150,100});
        Route parisAmsterdamRroute = new Route(new Station[]{paris1, paris2, amsterdam1, amsterdam2}, new int[]{0,100,50, 90});
        Route AmsterdamBerlinRroute = new Route(new Station[]{amsterdam1, amsterdam2, berlin1, berlin2}, new int[]{0,90, 100, 50});

        LocalDateTime parisLondoServiceTime = LocalDateTime.of(
                2021, Month.APRIL, 24, 14, 30, 0);
        Service parisLondoService = systemInventory.addService(parisLondoRroute, parisLondoServiceTime);

        Booking booking1 = new Booking(new Passenger("Youval Geva"), parisLondoService.getId(), "First Class","Paris1", "Paris2", 'A',3);
        Booking booking2 = new Booking(new Passenger("Bob Broun"), parisLondoService.getId(), "First Class","Paris1", "Paris2", 'A',4);
        Booking[] bookings = {booking1, booking2};

        //Simulate POST Request for Reservation
        Response postResponse = httpClient.post("/reservation", bookings);
        System.out.println("POST /reservation Status: " + postResponse.getStatusCode());
        System.out.println("POST /reservation Body: " + postResponse.getBody());

        // Simulate GET Request to Retrieve Booking
        Response getResponse = httpClient.get("/booking/" + booking1.getId());
        System.out.println("GET /booking Status: " + getResponse.getStatusCode());
        System.out.println("GET /booking Body: " + getResponse.getBody());
        assert parisLondoService.getId() == 1;

        // Failure
        bookings = new Booking[]{booking1};
        Response postResponseFailure = httpClient.post("/reservation", bookings);
        System.out.println("POST /reservation Status: " + postResponseFailure.getStatusCode());
        System.out.println("POST /reservation Body: " + postResponseFailure.getBody());

    }

}