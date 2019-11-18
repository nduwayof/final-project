package edu.mum.cs.deals.services;

import edu.mum.cs.deals.models.Deal;

import java.util.List;
import java.util.UUID;

/**
 * The interface Deal query service.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
public interface IDealQueryService {

    /**
     * Find all deals list.
     *
     * @return the list
     */
    List<Deal> findAllDeals();

    /**
     * Find deals by menu id list.
     *
     * @param menuId the menu id
     *
     * @return the list
     */
    List<Deal> findDealsByMenuId(final UUID menuId);

    /**
     * Find deal by id deal.
     *
     * @param id the id
     *
     * @return the deal
     */
    Deal findDealById(final UUID id);
}
