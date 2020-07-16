package io.spring.trucker.controller;

import io.spring.trucker.entity.Alert;
import io.spring.trucker.service.AlertService;
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
@Api(description = "Alert data  operation")
public class AlertController {

    @Autowired
    public AlertService alertService;

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create all alerts in db",
            notes = "return values with comma seperated json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "alert are created successfully"),
            @ApiResponse(code=404, message = "not found")
    })
    @Transactional
    public Alert create(@RequestBody Alert alert){

        return alertService.create(alert);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/vin/{vin}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return specified alert based on vehicle",
            notes = "return alert as json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "alerts of vehicle is returned successfully"),
            @ApiResponse(code=404, message = "not found")
    })
    @Transactional
    public List<Alert> getByVin(@PathVariable("vin") String vin){

        return alertService.getAlertsByVeh(vin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/priority/{priority}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return specified alert based on priority",
            notes = "return alert as json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "alerts are returned successfully based on priority"),
            @ApiResponse(code=404, message = "not found")
    })
    @Transactional
    public List<Alert> getByPri(@PathVariable("priority") String priority){

        return alertService.getAlertsByPri(priority);

    }
}