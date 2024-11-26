package Inventory;

import java.util.Arrays;

class Carriage {
    private static final int TOTAL_FIRST_CLASS_SEATS = 10;
    private static final int TOTAL_SECOND_CLASS_SEATS = 40;
    Seat[] seats;

     Carriage(int routSize) {
        this.seats = new Seat[TOTAL_FIRST_CLASS_SEATS + TOTAL_SECOND_CLASS_SEATS];
        createSeats(routSize);
    }

    private void createSeats(int routSize) {
        for (int i = 0; i < TOTAL_FIRST_CLASS_SEATS; i++) {
            this.seats[i]=new FirstClassSeat(routSize);
        }
        for (int i = TOTAL_FIRST_CLASS_SEATS; i < this.seats.length; i++) {
            this.seats[i] = new SecondClassSeat(routSize);
        }
    }

    public void reserveSeat(int seatNumber, int origin, int destination) throws IllegalArgumentException {
         seats[seatNumber].reserveSeat(origin,destination);
    }

    public boolean isSeatAvailable(int seatNumber, String seatType, int origin, int destination) throws IllegalArgumentException {
        isValidSeat(seatNumber);
        return seats[seatNumber].isSeatAvailable(seatType,origin,destination);
    }

    private void isValidSeat(int seatNumber) throws IllegalArgumentException {
        if (seatNumber < 0 || seatNumber >= this.seats.length) {
            throw new IllegalArgumentException("Invalid seat number");
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

