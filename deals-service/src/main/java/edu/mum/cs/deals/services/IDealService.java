package edu.mum.cs.deals.services;

import edu.mum.cs.deals.models.Deal;

import java.util.UUID;

/**
 * The interface Deal service.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
public interface IDealService {
    /**
     * Save deal deal.
     *
     * @param deal the deal
     *
     * @return the deal
     */
    Deal saveDeal(final Deal deal);

    /**
     * Delete deal by id deal.
     *
     * @param id the id
     *
     * @return the deal
     */
    Deal deleteDealById(final UUID id);
}
