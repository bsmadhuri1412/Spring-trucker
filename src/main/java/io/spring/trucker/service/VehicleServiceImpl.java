package io.spring.trucker.service;

import io.spring.trucker.Exception.VehicleNotFound;
import io.spring.trucker.entity.Vehicle;
import io.spring.trucker.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    public VehicleRepo vehicleRepo;

    @Override
    @Transactional
    public List<Vehicle> getAll() {
        Iterable<Vehicle> allVeh = vehicleRepo.findAll();
        return (List<Vehicle>) allVeh;
    }

    @Override
    @Transactional
    public Vehicle getVeh(String vid) {
        Optional<Vehicle> vehId = vehicleRepo.findByVin(vid);
        if(!vehId.isPresent()){
            throw new VehicleNotFound("Vehicle is "+vid+" is not present");
        }
        return vehId.get();
    }

    @Override
    @Transactional
    public List<Vehicle> create(List<Vehicle> veh) {
        System.out.println("Veh::"+veh);
        Iterable<Vehicle> vehicle = vehicleRepo.saveAll(veh);
        System.out.println("veh created");
        return (List<Vehicle>) vehicle;
    }

    @Override
    @Transactional
    public Vehicle update(Vehicle veh, String vid) {
        //Optional<Vehicle> byId = vehicleRepo.findByVin(vid);
//        if(!byId.isPresent()){
////            create(veh);
////        }
        Vehicle upVeh = vehicleRepo.save(veh);
        return upVeh;
    }

    @Override
    @Transactional
    public void delete(String vid) {

        vehicleRepo.deleteById(vid);
    }
}