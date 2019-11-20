package edu.mum.cs.restaurants.models;

import edu.mum.cs.restaurants.utilities.AbstractBaseEntity;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Restaurant.
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(value = "RESTAURANTS")
public class Restaurant extends AbstractBaseEntity implements Serializable {

    @Column(value = "NAME")
    private String name;

    @PrimaryKeyColumn(name = "PHONE_NUMBER", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private String phoneNumber;

    @Transient
    private List<RestaurantAddress> addresses = new ArrayList<>();

    @Transient
    private List<RestaurantMenu> menus = new ArrayList<>();

    @Transient
    private List<RestaurantSchedule> schedules = new ArrayList<>();

}
