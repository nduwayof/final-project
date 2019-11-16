package edu.mum.cs.restaurants.models;

import edu.mum.cs.restaurants.utilities.AbstractBaseEntity;
import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * The type Restaurant menu.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(value = "RESTAURANT_MENUS")
public class RestaurantMenu extends AbstractBaseEntity implements Serializable {

    @Column(value = "NAME")
    private String name;

    @Column(value = "DESCRIPTION")
    private String description;

    @Column(value = "PRICE")
    private BigDecimal price;

    @Column(value = "MENU_TAGS")
    private Set<String> menuTags;

    @Column(value = "DELIVERY_TIME")
    private long deliveryTime;

    @Column(value = "RESTAURANT_ID")
    private UUID restaurantId;

}
