package io.spring.trucker.repository;

import io.spring.trucker.entity.Readings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadingRepo extends CrudRepository<Readings, String> {


    Optional<Readings> findByRid(String s);

}