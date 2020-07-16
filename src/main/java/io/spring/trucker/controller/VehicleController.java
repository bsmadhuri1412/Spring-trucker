package io.spring.trucker.controller;

import io.spring.trucker.entity.Vehicle;
import io.spring.trucker.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
@CrossOrigin(origins = {"http://mocker.egen.io", "http://mocker.egen.academy"})
@Api(description = "Vehicles data  operation")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return all vehicles",
            notes = "return status values with comma seperated strings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "vehicles are returned successfully"),
            @ApiResponse(code = 500, message = "server error"),
            @ApiResponse(code=404, message = "not found")
    })
    public List<Vehicle> getAll(){

        return vehicleService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value ="/{vin}", produces =  MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return specified vehicles",
            notes = "return vehicle as json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "vehicle is returned successfully"),
            @ApiResponse(code=404, message = "not found")
    })
    public Vehicle findVehicle(@PathVariable("vin") String vId){
        Vehicle veh = vehicleService.getVeh(vId);
        System.out.println("returned veh:"+ veh.toString());
        return veh;
    }

    @RequestMapping(method = RequestMethod.POST,value="/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    @ApiOperation(value = "create all vehicles in db",
            notes = "return values with comma seperated json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "vehicles are created successfully"),
            @ApiResponse(code = 500, message = "server error"),
            @ApiResponse(code=404, message = "not found")
    })
    public List<Vehicle> create(@RequestBody List<Vehicle> vehicles)
    {
        // System.out.println("inside create");
//        List<Vehicle> list= new ArrayList<>();
//        for(Vehicle veh:vehicles){
//            Vehicle newVeh = vehicleService.create(veh);
//            list.add(newVeh);
//            System.out.println("New Veh check mate:"+newVeh.toString());
//        }
        List<Vehicle> allVeh = vehicleService.create(vehicles);


        return allVeh;
    }

    @RequestMapping(method = RequestMethod.PUT,value = {"/{vin}"},consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    @ApiOperation(value = "update all vehicles",
            notes = "return status values with comma seperated strings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "vehicles are updated successfully"),
            @ApiResponse(code = 500, message = "server error"),
            @ApiResponse(code=404, message = "not found")
    })
    public Vehicle update(@PathVariable("vin") String VId,@RequestBody Vehicle vehicle){
        Vehicle upVeh = vehicleService.update(vehicle, VId);
        System.out.println("update Veh: "+upVeh.toString());
        return upVeh;
    }

    @RequestMapping(method = RequestMethod.DELETE,value ={ "/{vin}"})
    //@ResponseBody
    @ApiOperation(value = "delete the vehicle",
            notes = "return status ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "vehicles is deleted successfully"),
            @ApiResponse(code=404, message = "not found")
    })
    public void delete(@PathVariable("vin") String vId){
        vehicleService.delete(vId);
        System.out.println("vehicle of id "+vId+" deleted");

    }



}
