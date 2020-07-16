package io.spring.trucker.service;
import io.spring.trucker.entity.Readings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReadingService {
    Readings getRead(String rid);
    List<Readings> getReadings();
    Readings create(Readings readings);
    Readings update(Readings readings, String rid);

}