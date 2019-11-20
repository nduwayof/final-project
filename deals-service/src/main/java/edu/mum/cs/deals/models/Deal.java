package edu.mum.cs.deals.models;

import edu.mum.cs.deals.utilities.AbstractBaseEntity;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


/**
 * The type Deal.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(value = "DEALS")
public class Deal extends AbstractBaseEntity {

    @Column(value = "NAME")
    private String name;

    @Column(value = "PRICE")
    private BigDecimal price;

    @Column(value = "MENU_ID")
    private UUID menuId;

    @Column(value = "START_DATE")
    private LocalDateTime startTime;

    @Column(value = "END_DATE")
    private LocalDateTime endTime;

    @Column(value = "ORDERS_LIMIT")
    private int ordersLimit;

}
