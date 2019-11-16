package edu.mum.cs.restaurants.models;

import edu.mum.cs.restaurants.utilities.AbstractBaseEntity;
import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

/**
 * The type Restaurant schedule.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(value = "RESTAURANT_SCHEDULES")
public class RestaurantSchedule extends AbstractBaseEntity implements Serializable {

    @PrimaryKeyColumn(name = "DAY", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private EWeekDay day;

    @Column(value = "OPENING")
    private LocalTime opening;

    @Column(value = "CLOSING")
    private LocalTime closing;

    @Column(value = "RESTAURANT_ID")
    private UUID restaurantId;
}
