package Inventory;

abstract class Seat {
    protected Boolean isOccupied;
    protected String type;
    protected int price;
    protected int width;
    protected int height;
    protected int passengerId;

    protected Seat() {
        this.isOccupied = false;
        this.passengerId = 0;
    }

    public void setOccupied(int passengerId) {
        this.isOccupied = true;
        this.passengerId = passengerId;
    }

    public int getPassengerId() {
        return isOccupied ? this.passengerId : 0;
    }

    public String getSeatType() {
        return this.type;
    }

    @Override
    public String toString() {
        return type + " Seat{" +
                "Occupied=" + isOccupied +
                (isOccupied ? ", passengerId=" + passengerId : "") +
                '}';
    }
}

