package edu.mum.cs.deals.repositories;

import edu.mum.cs.deals.models.Deal;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Deal repository.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@Repository
public interface IDealRepository extends CrudRepository<Deal, UUID> {

    @AllowFiltering
    Optional<Deal> findById(@NotNull final UUID id);

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
