package Inventory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/*
Singleton
LocalDB: Stores services data
*/

public class SystemInventory {
    // Singleton instance
    private static SystemInventory instance;

    // In-memory storage for trains
    private Map<Long, Service> services = new HashMap<>(); // <id,serviec>

    // Private constructor to prevent external instantiation
    private SystemInventory() {
        services = new HashMap<>();
    }

    // method to get the singleton instance
    public static SystemInventory getInstance() {
        if (instance == null) {
            instance = new SystemInventory();
        }
        return instance;
    }

    // Create and add a service to the storage
    public Service addService(Route rout, LocalDateTime departureTime) {
        Service service = new Service(rout, departureTime);
        this.services.put(service.getId(), service);
        return service;
    }

    // Get service by id
    public Service getServiceById(Long id) {
        return this.services.get(id);
    }

}
