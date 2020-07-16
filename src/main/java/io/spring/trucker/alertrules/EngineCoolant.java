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
@Rule(name = "Engine Coolant" , description = "check coolant and light")
public class EngineCoolant {
    @Autowired
    public VehicleRepo vehicleRepo;

    @Autowired
    public AlertService alertService;

    @Condition
    public boolean when(@Fact("engine coolant")Readings readings){
        Optional<Vehicle> existVeh = vehicleRepo.findById(readings.getVin());
        return existVeh.filter(vehicle -> readings.isEngineCoolantLow() || readings.isCheckEngineLightOn()).isPresent();
    }


    @Action
    public void then(@Fact("engine coolant") Readings readings){
        Date date= new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Alert alert = new Alert();
        alert.setVin(readings.getVin());
        alert.setPriority("LOW");
        alert.setDescription("low engine coolant / engine light");
        alert.setAlertGen(date);
        alertService.create(alert);
    }
}
