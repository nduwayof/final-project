package edu.mum.cs.restaurants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.mum.cs.restaurants.utilities.AbstractBaseEntity;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

/**
 * The type Address.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(value = "ADDRESSES")
public class RestaurantAddress extends AbstractBaseEntity implements Serializable {

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

    @Column(value = "RESTAURANT_ID")
    private UUID restaurantId;

    @JsonIgnore
    @Column(value = "LATITUDE")
    private double latitude;

    @JsonIgnore
    @Column(value = "LONGITUDE")
    private double longitude;

    @Transient
    private double [] coord = new double[2];

}
