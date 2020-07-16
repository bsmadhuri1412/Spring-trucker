package io.spring.trucker.repository;

import io.spring.trucker.entity.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepo extends CrudRepository<Alert,String> {

    Optional<Alert> findByAid(String s);

    List<Alert> findByVin(String vin);
    List<Alert> findByPriority(String priority);
}
