package com.information.roxbylink.events.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_customer_id_seq", sequenceName = "customer_customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_customer_id_seq")
    @Column(name = "customer_id", updatable = false)
    private Long customerId;

    @Column(name = "comm_name", length = 30, nullable = false)
    private String commName;

    @Column(name = "contact_person", length = 30)
    private String contactPerson;

    @Column(length = 10, nullable = false)
    private String phone;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(name = "post_address", length = 100, nullable = false)
    private String postAddress;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Event> event;
}
