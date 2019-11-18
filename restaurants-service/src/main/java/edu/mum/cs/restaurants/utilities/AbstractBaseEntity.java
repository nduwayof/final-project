package edu.mum.cs.restaurants.utilities;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * The type Abstract base entity.
 * @author nduwayofabrice
 * @version 1.0
 */
@Data
public abstract class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKeyColumn(
            name = "ID",
            ordinal = 2,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private UUID id;

    /**
     * Instantiates a new Abstract base entity.
     */
    protected AbstractBaseEntity() {
        this.id = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof AbstractBaseEntity)) {
            return false;
        }
        AbstractBaseEntity other = (AbstractBaseEntity) obj;
        return getId().equals(other.getId());
    }

}
