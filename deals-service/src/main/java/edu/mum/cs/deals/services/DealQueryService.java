package edu.mum.cs.deals.services;

import edu.mum.cs.deals.models.Deal;
import edu.mum.cs.deals.repositories.IDealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * The type Deal query service.
 * @author nduwayofabrice
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class DealQueryService implements IDealQueryService{

    private IDealRepository dealRepository;

    @Override
    public List<Deal> findAllDeals() {
        return (List<Deal>) this.dealRepository.findAll();
    }

    @Override
    public List<Deal> findDealsByMenuId(@NotNull final UUID menuId) {
        return this.dealRepository.findByMenuId(menuId);
    }

    @Override
    public Deal findDealById(@NotNull final UUID id) {
        return this.dealRepository.findById(id).orElse(null);
    }
}
