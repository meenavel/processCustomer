package com.cust.pc.service;

import com.cust.pc.dao.ProcessCustomerDAO;
import com.cust.pc.model.APIResponse;
import com.cust.pc.model.Customer;
import com.cust.pc.model.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessCustomerService {

    Logger logger = LoggerFactory.getLogger(ProcessCustomerService.class);

    @Autowired
    ProcessCustomerDAO processCustomerDAO;

    public ResponseEntity<APIResponse> addCustomer(List<Customer> customerList ){
        logger.info("Customer List " +customerList);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(0);
        apiResponse.setOperation("addCustomer");
        try{
            apiResponse.setCustomerList(processCustomerDAO.saveAll(customerList));
            //throw new Exception("Dummy Error Check");
            return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
        }
        catch(Exception ex){
            apiResponse.setStatus(-1);
            apiResponse.setErrorDetails(new ErrorDetails(100, ex.getMessage()));
            return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<APIResponse> getCustomerById( long id){
        logger.info("Requested Id ::: " +id);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(0);
        apiResponse.setOperation("getCustomer");
        List<Customer> customerList = new ArrayList<Customer>();
        Customer customer = processCustomerDAO.getById(id);
        try{
            if(customer.getFirstName() != null) {
                customerList.add(processCustomerDAO.getById(id));
            }
        }
        catch(Exception ex){
            logger.info("Exception ::: "+ ex.getMessage());
            apiResponse.setErrorDetails(new ErrorDetails(100, "No record(s) found for ::: " + id));
        }
        apiResponse.setCustomerList(customerList);
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
    }

    public ResponseEntity<APIResponse> getCustomerByIdList(List<Long> idList){
        logger.info("Requested Id List ::: " +idList);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(0);
        apiResponse.setOperation("get customer by ID");
        List<Customer> customerList = new ArrayList<Customer>();
        List<Long> idNFList = new ArrayList<Long>();
        //Customer customer = null;
        for(long i: idList ){
            try{
                Customer customer = processCustomerDAO.getById(i);
                if(customer.getFirstName() != null) {
                    logger.info("Customer :::: " + i);
                    customerList.add(customer);
                }
            }
            catch(Exception ex){
                logger.info("Exception ::: "+ ex.getMessage());
                idNFList.add(i);
            }
        }
        apiResponse.setCustomerList(customerList);
        if(idNFList.size() > 0){
            apiResponse.setErrorDetails(new ErrorDetails(100, "No record(s) found for ::: "+idNFList));
        }
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);

    }
    public ResponseEntity<APIResponse> getAllCustomer(){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(0);
        apiResponse.setOperation("get all Customers");
        List<Customer> customerList = new ArrayList<Customer>();
        customerList = processCustomerDAO.findAll();
        if( customerList.size()>0) {
            logger.info("Customer List :::" + customerList);
            apiResponse.setCustomerList(customerList);
        }
        else{
            apiResponse.setErrorDetails(new ErrorDetails(100,"No Records Found"));
        }
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
    }

}
