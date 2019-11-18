package edu.mum.cs.deals.repositories;

import edu.mum.cs.deals.models.Deal;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * The interface Deal repository.
 * @author nduwayofabrice
 * @version 1.0
 */
@Repository
public interface IDealRepository extends CrudRepository<Deal, UUID> {

    /**
     * Find by menu id list.
     *
     * @param menuId the menu id
     *
     * @return the list
     */
    @AllowFiltering
    List<Deal> findByMenuId(final UUID menuId);
}
