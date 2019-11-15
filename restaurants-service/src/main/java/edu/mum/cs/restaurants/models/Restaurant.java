package edu.mum.cs.restaurants.models;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table(value = "RESTAURANTS")
public class Restaurant{

    @PrimaryKey
    private long id;

    @Column(value = "name")
    private String name;

}
