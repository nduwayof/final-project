package edu.mum.cs.deals.controllers;

import edu.mum.cs.deals.configurations.DealsFeignProxy;
import edu.mum.cs.deals.models.Deal;
import edu.mum.cs.deals.services.IDealQueryService;
import edu.mum.cs.deals.services.IDealService;
import edu.mum.cs.deals.utilities.ResourceNotFoundException;
import edu.mum.cs.deals.utilities.beans.RestaurantMenuBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type Deal controller.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/deals/api/v1")
public class DealController {

    private IDealService dealService;

    private IDealQueryService dealQueryService;

    private DealsFeignProxy proxy;

    /**
     * Post deal deal.
     *
     * @param deal the deal
     *
     * @return the deal
     */
    @PostMapping
    @CachePut(value = "deals", key = "#deal")
    @ApiOperation(value = "View a list of available deals", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Deal postDeal(@RequestBody Deal deal) throws ResourceNotFoundException {
        Deal dealObj = null;
        if (deal.getMenuId() != null) {
            RestaurantMenuBean restaurantMenu = proxy.getRestaurantMenu(deal.getMenuId());
            if (restaurantMenu != null)
                dealObj = this.dealService.saveDeal(deal);
        } else {
            throw new ResourceNotFoundException("Menu item not found");
        }
        return dealObj;
    }

    /**
     * Delete deal deal.
     *
     * @param id the id
     *
     * @return the deal
     */
    @DeleteMapping(value = "/delete/{id}")
    @CacheEvict(value = "deals", key = "#id")
    @ApiOperation(value = "Deleting a deal by id", response = Deal.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted a deal"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Deal deleteDeal(@PathVariable("id") UUID id) {
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
    @Cacheable(value = "deals", key = "#id")
    @ApiOperation(value = "View a list of available deals", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Deal getDeal(@PathVariable("id") UUID id) {
        return this.dealQueryService.findDealById(id);
    }

    /**
     * Get deals list.
     *
     * @return the list
     */
    @GetMapping
    @Cacheable(value = "deals")
    @ApiOperation(value = "View a list of available deals", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved a list deals"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public List<Deal> getDeals() {
        return this.dealQueryService.findAllDeals();
    }

    /**
     * Gets deals by menu.
     *
     * @param id the id
     *
     * @return the deals by menu
     */
    @GetMapping(value = "/menu/{id}")
    @Cacheable(value = "deals", key = "#id")
    @ApiOperation(value = "View a list of available deals by menu", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved a list deals by menu"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public List<Deal> getDealsByMenu(@PathVariable("id") UUID id) {
        return this.dealQueryService.findDealsByMenuId(id);

    }

}
