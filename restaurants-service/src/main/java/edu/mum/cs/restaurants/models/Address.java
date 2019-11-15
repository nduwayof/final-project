package edu.mum.cs.restaurants.models;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table(value = "ADDRESSES")
public class Address {

    @PrimaryKey
    private long id;

    @Column(value = "STREET")
    private String street;

    @Column(value = "CITY")
    private String city;

    @Column(value = "STATE")
    private String state;

    @Column(value = "ZIP_CODE")
    private int zipCode;

    @Column(value = "COUNTRY")
    private String country;

}
