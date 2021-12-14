package com.cust.pc.controller;

import com.cust.pc.model.APIResponse;
import com.cust.pc.model.Customer;
import com.cust.pc.service.ProcessCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcessCustomerController {

    Logger logger = LoggerFactory.getLogger(ProcessCustomerController.class);

    @Autowired
    ProcessCustomerService processCustomerService;

    //Add Customer

    @PostMapping(path = "/addCustomer")
    public ResponseEntity<APIResponse> addCustomer(@RequestBody List<Customer> customerList ){
        return processCustomerService.addCustomer(customerList);
    }

    //Get Customer by ID

    @GetMapping(path = "/getCustomerById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse> getCustomerById(@PathVariable ("id") long id){
        //logger.info("Customer List" +customerList);
        return processCustomerService.getCustomerById(id);
    }

    //Get Customer By Id list
    @PostMapping(path = "/getCustomerByIdList")
    public ResponseEntity<APIResponse> getCustomerByIdList(@RequestBody List<Long> idList){
        return processCustomerService.getCustomerByIdList(idList);
    }

    //Get all Customers

    @GetMapping(path="/getAllCustomers")
    public ResponseEntity<APIResponse> getAllCustomer(){
        return processCustomerService.getAllCustomer();
    }

}
