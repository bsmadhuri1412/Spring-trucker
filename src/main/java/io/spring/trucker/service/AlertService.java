package io.spring.trucker.service;

import io.spring.trucker.entity.Alert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlertService {
    Alert create(Alert alert);
    List<Alert> getAlertsByVeh(String vin);
    List<Alert> getAlertsByPri(String priority);
}