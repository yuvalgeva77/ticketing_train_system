import Inventory.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        Station paris = new Station("paris");
        Station amsterdam = new Station("amsterdam");
        Station berlin = new Station("berlin");

        Station[] arr1 = {paris, amsterdam, berlin};
        int[] arr2 = {0, 5, 6};
        Route route = new Route(arr1, arr2);

        LocalDateTime localDateTime = LocalDateTime.of(
                2021, Month.APRIL, 24, 14, 33, 48);

        Service service = new Service(route, localDateTime);
        System.out.println(service.toString());


    }

}