package edu.mum.cs.deals.models;

import edu.mum.cs.deals.utilities.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;


@Data
@Table
@EqualsAndHashCode(callSuper = true)
public class Deal extends AbstractBaseEntity {
}
