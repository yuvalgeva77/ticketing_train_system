package Ticketing;

import java.util.ArrayList;

public class Passenger {
    private String name;
    private ArrayList<Ticket> tickets;


    public Passenger(String name) {
        this.name = name;
        this.tickets=new ArrayList<>();
    }

    public Passenger(String name, ArrayList<Ticket> tickets) {
        this.name = name;
        this.tickets = tickets;
    }

    public void addTicket(){

    }

}
