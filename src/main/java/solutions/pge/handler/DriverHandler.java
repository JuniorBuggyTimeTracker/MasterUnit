package solutions.pge.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import solutions.pge.models.business.Club;
import solutions.pge.models.business.Driver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DriverHandler {

    private final List<Driver> drivers = new LinkedList<>();

    public void init() throws IOException {
        var inputStream = getClass().getClassLoader().getResourceAsStream("demo-racers.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Driver> demoDrivers = mapper.readValue(inputStream, List.class);
        drivers.addAll(demoDrivers);
    }

    public Driver add(Driver driver){
        this.drivers.add(driver);
        return driver;
    }

    public Driver findByRacerId(UUID driverId){
        return drivers.stream().filter(r -> r.driverId().equals(driverId)).findFirst().orElseThrow(NotFoundException::new);
    }

    public List<Driver> findALl(){
        return drivers;
    }

    public void delete(UUID driverId){
        var racer = this.findByRacerId(driverId);
        drivers.remove(racer);
    }
}
