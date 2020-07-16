package io.spring.trucker.service;
import io.spring.trucker.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    List<Vehicle> getAll();
    Vehicle getVeh(String vid);
    List<Vehicle> create(List<Vehicle> veh);
    Vehicle update(Vehicle veh, String vid);
    void delete(String vid);
}
