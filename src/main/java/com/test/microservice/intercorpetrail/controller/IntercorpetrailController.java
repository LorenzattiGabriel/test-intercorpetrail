package com.test.microservice.intercorpetrail.controller;

import com.test.microservice.intercorpetrail.exceptions.BussinesLogicException;
import com.test.microservice.intercorpetrail.model.Person;
import com.test.microservice.intercorpetrail.service.IntercorpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="Persons Management System", description="Several operations in a list of persons")
@RestController
@RequestMapping(value = "/api/persons/")
public class IntercorpetrailController {


    private IntercorpService intercorpService;
    private static Logger logger = LoggerFactory.getLogger(IntercorpetrailController.class);

    @Autowired
    public IntercorpetrailController(IntercorpService intercorpService) {
        this.intercorpService = intercorpService;
    }

    @ApiOperation(value = "Save a new Person", response = HttpStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved person"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    public ResponseEntity<Person> postNewPerson(@RequestBody Person person) throws BussinesLogicException {
        intercorpService.registerNewPerson(person);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}