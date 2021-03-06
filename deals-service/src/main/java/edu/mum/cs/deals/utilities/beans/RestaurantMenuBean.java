package edu.mum.cs.deals.utilities.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * The type Restaurant menu bean.
 * @author nduwayofabrice
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenuBean {
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> menuTags;
    private long deliveryTime;
    private UUID restaurantId;
}
