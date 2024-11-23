package Inventory;

public class SecondClassSeat extends Seat {
    public SecondClassSeat(int routSize) {
        super(routSize);
        this.type = "Second Class";
        this.price=2;
        this.width=60;
        this.height =30;
    }
}
