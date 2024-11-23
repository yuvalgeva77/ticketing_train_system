package Inventory;

import java.util.Arrays;

class Stop {
    Station station;
    int distanceFroPrev;

    Stop(Station station, int distanceFroPrev) {
        this.station = station;
        this.distanceFroPrev = distanceFroPrev;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "Station=" + station.name +
                ", Distance=" + distanceFroPrev +
                '}';
    }
}

public class Route {
    private Stop[] stops;

    public Route(Station[] stations, int[] stationsDistances) throws IllegalArgumentException {
        if (stations == null || stations.length == 0) {
            throw new IllegalArgumentException("Stations cant be empty");
        }
        // Check if sizes match
        if (stations.length != stationsDistances.length) {
            throw new IllegalArgumentException("Stations and StationDistance must have the same size");
        }
        // Check if sizes match
        if (stationsDistances[0]!=0) {
            throw new IllegalArgumentException("first element in StationDistance must be 0");
        }

        createStops(stations, stationsDistances);
    }

    private void createStops(Station[] stations, int[] stationsDistances) {
       this.stops=new Stop[stations.length];
        for (int i = 0; i < stations.length; i++) {
            if (stationsDistances[i]<0) {
                throw new IllegalArgumentException("Distance cant be negative");
            }
            this.stops[i] = new Stop(stations[i], stationsDistances[i]);
        }
    }

    public Stop[] getStops() {
        return this.stops;
    }

    @Override
    public String toString() {
        return Arrays.toString(stops);
    }
}
