package edu.mum.cs.deals.services;

import edu.mum.cs.deals.models.Deal;
import edu.mum.cs.deals.repositories.IDealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * The type Deal service.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class DealService implements IDealService{

    private IDealRepository dealRepository;

    @Override
    public Deal saveDeal(@NotNull final Deal deal) {
        return this.dealRepository.save(deal);
    }

    @Override
    public Deal deleteDealById(@NotNull final UUID id) {
        Deal deal = this.dealRepository.findById(id).orElse(null);
        this.dealRepository.deleteById(id);
        return deal;
    }
}
