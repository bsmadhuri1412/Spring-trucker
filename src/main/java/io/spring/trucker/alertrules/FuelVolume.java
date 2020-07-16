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
import org.jeasy.rules.core.BasicRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
@Rule(name = "Fuel Volume", description = "Fuel is 10% less than maxFuel")
public class FuelVolume extends BasicRule {

    @Autowired
    public VehicleRepo vehicleRepo;

    @Autowired
    public AlertService alertService;

    @Condition
    public Boolean when(@Fact("fuel volume") Readings readings){
        Optional<Vehicle> existVeh = vehicleRepo.findById(readings.getVin());
        return existVeh.filter(vehicle -> readings.getFuelVolume()<0.1*vehicle.getMaxFuelVolume()).isPresent();
    }

    @Action
    public void then(@Fact("fuel volume") Readings readings){
        Date date= new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Alert alert = new Alert();
        alert.setVin(readings.getVin());
        alert.setPriority("MEDIUM");
        alert.setDescription("Controllable fuel volume");
        alert.setAlertGen(date);
        alertService.create(alert);
    }
}
