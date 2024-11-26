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
        Route LondonParisRroute = new Route(new Station[]{london2, london1, paris2, paris1 }, new int[]{0,100,150,100});
        Route parisAmsterdamRroute = new Route(new Station[]{paris1, paris2, amsterdam1, amsterdam2}, new int[]{0,100,50, 90});
        Route AmsterdamBerlinRroute = new Route(new Station[]{amsterdam1, amsterdam2, berlin1, berlin2}, new int[]{0,90, 100, 50});

        LocalDateTime parisLondonServiceTime = LocalDateTime.of(
                2021, Month.APRIL, 1, 14, 30, 0);
        Service parisLondoService = systemInventory.addService(parisLondoRroute, parisLondonServiceTime);

        LocalDateTime LondonParisRouteServiceTime = LocalDateTime.of(
                2021, Month.APRIL, 7, 14, 30, 0);
        Service LondonParisService = systemInventory.addService(LondonParisRroute, LondonParisRouteServiceTime);

        Booking booking1 = new Booking(new Passenger("Youval Geva"), parisLondoService.getId(), "First Class","Paris1", "Paris2", 'A',11);
        Booking booking2 = new Booking(new Passenger("Bob Broun"), parisLondoService.getId(), "First Class","Paris1", "Paris2", 'A',12);
        Booking[] bookingsParisLondon = {booking1, booking2};

        Booking booking3 = new Booking(new Passenger("Youval Geva"), LondonParisService.getId(), "First Class","London2", "Paris1", 'C',7);
        Booking booking4 = new Booking(new Passenger("Bob Broun"), LondonParisService.getId(), "Second Class","London2", "Paris1", 'B',41);
        Booking booking5 = new Booking(new Passenger("Youval Geva"), parisLondoService.getId(), "First Class","Paris1", "London2", 'C',7);
        Booking booking6 = new Booking(new Passenger("Bob Broun"), parisLondoService.getId(), "Second Class","Paris1", "London2", 'B',41);
        Booking[] bookingParisLondoAndReverse = {booking3, booking4, booking5, booking6};

        //Simulate POST Request for Reservation
        Response postResponse = httpClient.post("/reservation", bookingParisLondoAndReverse);
        System.out.println("POST /reservation Status: " + postResponse.getStatusCode());
        System.out.println("POST /reservation Body: " + postResponse.getBody());

        // Simulate GET Request to Retrieve Booking
        Response getResponse = httpClient.get("/booking/" + booking1.getId());
        System.out.println("GET /booking Status: " + getResponse.getStatusCode());
        System.out.println("GET /booking Body: " + getResponse.getBody());
        assert parisLondoService.getId() == 1;

        // Failure
        Response postResponseFailure = httpClient.post("/reservation", bookingsParisLondon);
        System.out.println("POST /reservation Status: " + postResponseFailure.getStatusCode());
        System.out.println("POST /reservation Body: " + postResponseFailure.getBody());

    }

}