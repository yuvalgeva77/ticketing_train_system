package Inventory;

import java.util.Arrays;

class Carriage {
    private static final int TOTAL_FIRST_CLASS_SEATS = 10;
    private static final int TOTAL_SECOND_CLASS_SEATS = 40;
    Seat[] seats;

     Carriage() {
        this.seats = new Seat[TOTAL_FIRST_CLASS_SEATS + TOTAL_SECOND_CLASS_SEATS];
        createSeats();
    }

    private void createSeats() {
        for (int i = 0; i < TOTAL_FIRST_CLASS_SEATS; i++) {
            this.seats[i]=new FirstClassSeat();
        }
        for (int i = TOTAL_FIRST_CLASS_SEATS; i < this.seats.length; i++) {
            this.seats[i] = new SecondClassSeat();
        }
    }

    public int getTotalSeats() {
        return TOTAL_FIRST_CLASS_SEATS + TOTAL_SECOND_CLASS_SEATS;
    }

    @Override
    public String toString() {
        return "\nCarriage{" +
                Arrays.toString(seats) +
                '}';
    }
}

