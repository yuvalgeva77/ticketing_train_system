package Inventory;

public abstract class Seat {
    protected boolean[] occupiedInStop;
    protected String type;
    protected int price;
    protected int width;
    protected int height;

    protected Seat(int routSize) {
        this.occupiedInStop = new boolean[routSize];
    }

    public void reserveSeat(int origin, int destination) throws IllegalArgumentException {
        isValidateRange(origin, destination);
        for (int i=origin;i<=destination;i++) {
            this.occupiedInStop[i] = true;
        }
    }

    public boolean isSeatAvailable(String seatType, int origin, int destination) throws IllegalArgumentException {
        return this.type.equals(seatType) && isSeatAvailableInRange(origin, destination);
    }

    public Boolean isSeatAvailableInRange(int origin, int destination) throws IllegalArgumentException {
        isValidateRange(origin, destination);
        for (int i=origin;i<=destination;i++) {
            if (this.occupiedInStop[i]){
                return false;
            }
        }
        return true;
    }

    private void isValidateRange(int origin, int destination) throws IllegalArgumentException {
        if (origin > destination || origin < 0 || destination >= this.occupiedInStop.length) {
            throw new IllegalArgumentException("Invalid stops range");
        }
    }

    public String getSeatType() {
        return this.type;
    }

    private String getOccupiedStops(){
        String occupied = "";
        for (int i=0;i<occupiedInStop.length;i++) {
            if(this.occupiedInStop[i]){
                occupied += (occupied.equals("")) ? i : ", " + i;
            }
        }
        return occupied;
    }

    @Override
    public String toString() {
        String occupiedStops = getOccupiedStops();
        String occupiedStopsString = (!occupiedStops.equals("")) ? "Occupied stops = " + occupiedStops : "Free";

        return type + " Seat{" +
                occupiedStopsString + '}';
    }
}

