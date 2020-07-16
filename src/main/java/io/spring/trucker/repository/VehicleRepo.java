package io.spring.trucker.repository;


import io.spring.trucker.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepo extends CrudRepository<Vehicle, String> {

    Optional<Vehicle> findByVin(String s);
}
