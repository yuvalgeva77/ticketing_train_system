import Inventory.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        SystemInventory systemInventory = SystemInventory.getInstance();

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
        assert parisLondoService.getId() == 1;
        //System.out.println(parisLondoService.toString());
        try{
           parisLondoService.isSeatAvailable('A',1,"First Class", "Paris1", "Paris2");
           parisLondoService.saveSeat('A',1, "Paris1", "Paris2");
           System.out.println(parisLondoService.toString());
           parisLondoService.isSeatAvailable('A', 1, "First Class", "Paris1", "London2");

           parisLondoService.isSeatAvailable('A',1,"First Class", "London1", "London2");
           parisLondoService.saveSeat('A',1, "London1", "London2");
           System.out.println(parisLondoService.toString());

        }
        catch (Exception e){
            System.out.println(e);
        }



//
//        TicketingService ticketingService = new TicketingService();
//        HTTPClient client = new MockHTTPClient(ticketingService);
//
//        // Create a ticket
//        Ticket ticket = new Ticket("1", "Fix the server issue");
//        Response postResponse = client.post("/tickets", ticket);
//        System.out.println("POST Response: " + postResponse.getStatusCode() + " - " + postResponse.getBody());
//
//        // Get the ticket
//        Response getResponse = client.get("/tickets/1");
//        System.out.println("GET Response: " + getResponse.getStatusCode() + " - " + getResponse.getBody());

    }

}