package Inventory;

public class FirstClassSeat extends Seat {
    public FirstClassSeat(int routSize) {
        super(routSize);
        this.type = "First Class";
        this.price=4;
        this.width=70;
        this.height =50;
    }
}
