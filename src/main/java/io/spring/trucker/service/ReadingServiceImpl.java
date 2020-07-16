package io.spring.trucker.service;

import io.spring.trucker.Exception.BadRequestException;
import io.spring.trucker.Exception.ReadingNotFound;
import io.spring.trucker.entity.Alert;
import io.spring.trucker.entity.Readings;
import io.spring.trucker.entity.Vehicle;
import io.spring.trucker.repository.ReadingRepo;
import io.spring.trucker.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    ReadingRepo readRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    AlertService alertService;

    @Override
    public Readings getRead(String rid) {
        Optional<Readings> existRe = readRepo.findByRid(rid);
        if(!existRe.isPresent()){
            throw new ReadingNotFound("readings of the vehicle is not found");
        }

        return existRe.get();
    }

    @Override
    public List<Readings> getReadings() {

        Iterable<Readings> allReadings = readRepo.findAll();

        return (List<Readings>) allReadings;
    }

    @Override
    public Readings create(Readings readings) {
        Optional<Vehicle> exitsVeh = vehicleRepo.findByVin(readings.getVin());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy, HH:mm:ss aaa");
        try{
            date = sdf.parse(readings.getTimestamp());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(exitsVeh.isPresent()){
            // engine rpm > read line rpm

            if(readings.getEngineRpm()>exitsVeh.get().getRedlineRpm()){
                Alert alert = new Alert();
                alert.setVin(readings.getVin());
                alert.setPriority("HIGH");
                alert.setAlertGen(date);
                alert.setDescription("High Engine RPM");
                alertService.create(alert);
                System.out.println("Alert for vehicle with High RPM created::");
            }

            //fuel volume 10% of max fuel volume
            if(readings.getFuelVolume() < 0.1 * exitsVeh.get().getMaxFuelVolume()){
                Alert alert = new Alert();
                alert.setVin(readings.getVin());
                alert.setPriority("MEDIUM");
                alert.setAlertGen(date);
                alert.setDescription("fuel volume is 10% less");
                alertService.create(alert);
                System.out.println("Alert for vehicle with fuel volume created::");

            }

            //tire pressure between 32 and 36
            if(readings.getTires().getFrontLeft()<32 || readings.getTires().getFrontLeft()>36 || readings.getTires().getFrontRight()<32 || readings.getTires().getFrontRight()>36 || readings.getTires().getRearLeft()<32 || readings.getTires().getRearLeft()>36 || readings.getTires().getRearRight()<32 || readings.getTires().getRearRight()>36){
                Alert alert = new Alert();
                alert.setVin(exitsVeh.get().getVin());
                alert.setPriority("LOW");
                alert.setAlertGen(date);
                alert.setDescription("Tire pressure not between 32 and 36 ");
                alertService.create(alert);
                System.out.println("Alert for vehicle with check tires created::");

            }

            //engine coolant

            if(readings.isEngineCoolantLow() || readings.isCheckEngineLightOn()){
                Alert alert = new Alert();
                alert.setVin(readings.getVin());
                alert.setPriority("LOW");
                alert.setDescription("low engine coolant / engine light");
                alert.setAlertGen(date);
                alertService.create(alert);
                System.out.println("Alert for vehicle with engine coolant created::");
            }
        }

        Readings read = readRepo.save(readings);
        return read;
    }

    @Override
    public Readings update(Readings readings, String rid) {
        Optional<Readings> byId = readRepo.findByRid(rid);
        if(!byId.isPresent()){
            throw new BadRequestException("Reading for the vehicle is not present");
        }
        Readings updateRead = readRepo.save(readings);
        return updateRead;
    }
}
