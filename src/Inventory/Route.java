package Inventory;

import java.util.Arrays;
import java.util.HashMap;

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
    private HashMap<String, Integer> stopNumberByStationName; // <name, stopNumber>

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

    private void createStops(Station[] stations, int[] stationsDistances) throws IllegalArgumentException {
       this.stops=new Stop[stations.length];
        this.stopNumberByStationName=new HashMap<>();

        for (int i = 0; i < stations.length; i++) {
            if (stationsDistances[i]<0) {
                throw new IllegalArgumentException("Distance cant be negative");
            }
            this.stops[i] = new Stop(stations[i], stationsDistances[i]);
            stopNumberByStationName.put(stations[i].name, i);
        }
    }

    public int getStationNumberByName(String stationName) throws IllegalArgumentException {
        Integer stopNumber = this.stopNumberByStationName.get(stationName);
        if(stopNumber == null){
            throw new IllegalArgumentException("No such station in rout");
        }
        return stopNumber;
    }

    public int getRoutSize(){
        return this.stops.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(stops);
    }
}
