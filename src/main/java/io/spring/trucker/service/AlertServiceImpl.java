package io.spring.trucker.service;

import io.spring.trucker.Exception.AlertNotFound;
import io.spring.trucker.entity.Alert;
import io.spring.trucker.repository.AlertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService{

    @Autowired
    public AlertRepo alertRepo;

    @Override
    public Alert create(Alert alert) {
//        Optional<Alert> check = alertRepo.findByAid(alert.getAid());
//        if(check.isPresent()){
//            throw new BadRequestException("alert already found");
//        }
        Alert created = alertRepo.save(alert);

        return created;
    }

    @Override
    public List<Alert> getAlertsByVeh(String vin) {
        List<Alert> getal = alertRepo.findByVin(vin);
        if(getal.get(0)==null){
            throw new AlertNotFound("alert does not exits");
        }

        return getal;
    }

    @Override
    public List<Alert> getAlertsByPri(String priority) {
        List<Alert> byPriority = alertRepo.findByPriority(priority);
        if(byPriority.get(0)==null){
            throw new AlertNotFound("alert with the specified priority does not exist");
        }
        return byPriority;
    }
}
