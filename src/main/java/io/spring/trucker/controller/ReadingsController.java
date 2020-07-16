package io.spring.trucker.controller;

import io.spring.trucker.entity.Readings;
import io.spring.trucker.service.ReadingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://mocker.egen.io", "http://mocker.egen.academy"})

@RequestMapping(value = "/readings")
@Api(description = "Readings data  operation")
public class ReadingsController {

    @Autowired
    private ReadingService readingService;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return all Readings",
            notes = "return values with comma seperated strings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "readings are returned successfully"),
            @ApiResponse(code = 500, message = "server error"),
            @ApiResponse(code=404, message = "not found")
    })
    @Transactional
    public List<Readings> getAll(){

        return readingService.getReadings();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return readings specified vehicle",
            notes = "return vehicle as json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "reading of vehicle is returned successfully"),
            @ApiResponse(code=404, message = "not found")
    })
    public Readings getRead(@PathVariable("vin") String vid)
    {
        Readings read = readingService.getRead(vid);
        System.out.println("reading:"+read);
        return read;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create all readings in db",
            notes = "return values with comma seperated json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "readings are created successfully"),
            @ApiResponse(code = 500, message = "server error"),
            @ApiResponse(code=404, message = "not found")
    })
    @Transactional
    public Readings create(@RequestBody Readings readings){
        System.out.println("creating reading::");
        Readings newRead = readingService.create(readings);
        System.out.println("new reading:"+newRead);
        return newRead;
    }

//    @RequestMapping(method = RequestMethod.PUT,value = "{rid}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    @Transactional
//    public Readings update(@PathVariable("rid") String rid,@RequestBody Readings readings){
//        Readings upRead = readingService.update(readings, rid);
//        System.out.println("update reading:"+upRead);
//        return upRead;
//    }



}