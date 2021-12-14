package com.cust.pc.dao;

import com.cust.pc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProcessCustomerDAO extends JpaRepository<Customer, Long> {

}
