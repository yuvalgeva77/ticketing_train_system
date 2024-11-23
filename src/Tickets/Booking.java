package Tickets;

import java.util.Set;

public class Booking {
    private static long counter = 0;
    private long id;
    private Set<Ticket> tickets;

    public Booking(long id, Set<Ticket> tickets) {
        this.id = id;
        this.tickets = tickets;
    }
}
