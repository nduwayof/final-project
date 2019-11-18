package edu.mum.cs.deals.controllers;

import edu.mum.cs.deals.models.Deal;
import edu.mum.cs.deals.services.IDealQueryService;
import edu.mum.cs.deals.services.IDealService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type Deal controller.
 * @author nduwayofabrice
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/deals/api/v1")
public class DealController {

    private IDealService dealService;

    private IDealQueryService dealQueryService;

    /**
     * Post deal deal.
     *
     * @param deal the deal
     *
     * @return the deal
     */
    @PostMapping
    public Deal postDeal(@RequestBody Deal deal){
        return this.dealService.saveDeal(deal);
    }

    /**
     * Delete deal deal.
     *
     * @param id the id
     *
     * @return the deal
     */
    @DeleteMapping(value = "/delete/{id}")
    public Deal deleteDeal(@PathVariable("id") UUID id){
        return this.dealService.deleteDealById(id);
    }

    /**
     * Gets deal.
     *
     * @param id the id
     *
     * @return the deal
     */
    @GetMapping(value = "/{id}")
    public Deal getDeal(@PathVariable("id") UUID id){
        return this.dealQueryService.findDealById(id);
    }

    /**
     * Get deals list.
     *
     * @return the list
     */
    @GetMapping
    public List<Deal> getDeals(){
        return this.dealQueryService.findAllDeals();
    }

}
