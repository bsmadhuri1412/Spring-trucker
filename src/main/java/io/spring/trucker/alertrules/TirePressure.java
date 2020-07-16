package io.spring.trucker.alertrules;

import io.spring.trucker.entity.Alert;
import io.spring.trucker.entity.Readings;
import io.spring.trucker.entity.Vehicle;
import io.spring.trucker.repository.VehicleRepo;
import io.spring.trucker.service.AlertService;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
@Rule(name = "Tire Pressure", description = "Tire pressure between 32psi - 36 psi")
public class TirePressure {

    @Autowired
    public VehicleRepo vehicleRepo;

    @Autowired
    public AlertService alertService;

    @Condition
    public boolean when(@Fact("Tire pressure") Readings readings){
        boolean res=false;
        try{
            Optional<Vehicle> existVeh = vehicleRepo.findById(readings.getVin());
            res= existVeh.filter(vehicle -> readings.getTires().getFrontLeft()>32 || readings.getTires().getFrontLeft()<36 || readings.getTires().getFrontRight()>32 || readings.getTires().getFrontRight()<36 || readings.getTires().getRearLeft()>32 || readings.getTires().getRearLeft()<36 || readings.getTires().getRearRight()>32 || readings.getTires().getRearRight()<36 ).isPresent();

        }
        catch (Exception e){
            e.getMessage();
        }
        return res;

    }

    @Action
    public void then(@Fact("tire pressure") Readings readings){
        Date date= new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Alert alert = new Alert();
        alert.setVin(readings.getVin());
        alert.setPriority("LOW");
        alert.setDescription("Tire Pressure");
        alert.setAlertGen(date);
        alertService.create(alert);

    }
}

