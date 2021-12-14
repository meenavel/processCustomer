package com.cust.pc.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse {
    private int status;
    private String operation;
    private List<Customer> customerList;
    private ErrorDetails errorDetails;
}
