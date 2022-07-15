package com.information.roxbylink.events.repositories;

import com.information.roxbylink.events.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "INSERT INTO customer (comm_name, contact_person, email, phone, post_address) VALUES (?1, ?2, ?3, ?4, ?5) RETURNING *", nativeQuery = true)
    Customer insertCustomer(String commName, String contactPerson, String email, String phone, String postAdd);

    @Modifying
    @Query(value = "UPDATE customer SET comm_name = ?1, contact_person = ?2, email = ?3, phone = ?4, post_address = ?5 WHERE customer_id = ?6 RETURNING *", nativeQuery = true)
    void updateCustomer(String commName, String contactPerson, String email, String phone, String postAdd, Long customerId);

    @Query(value = "SELECT * FROM customer WHERE customer_id = ?1", nativeQuery = true)
    Customer getCustomerById(Long customerId);
}
