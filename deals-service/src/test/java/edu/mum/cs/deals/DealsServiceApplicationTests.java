package edu.mum.cs.deals;

import edu.mum.cs.deals.models.Deal;
import edu.mum.cs.deals.repositories.IDealRepository;
import edu.mum.cs.deals.services.IDealQueryService;
import edu.mum.cs.deals.services.IDealService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Deals service application tests.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DealsServiceApplicationTests {

    @MockBean
    private IDealRepository dealRepository;

    @Autowired
    private IDealService dealService;

    @Autowired
    private IDealQueryService dealQueryService;

    @Test
    public void getDealsTest(){
        when(dealRepository.findAll()).thenReturn(Stream.of(
                new Deal("Every Body", BigDecimal.TEN, UUID.randomUUID() , LocalDateTime.now(),
                        LocalDateTime.now(), 45)).collect(Collectors.toList()));
        assertEquals(1, dealQueryService.findAllDeals().size());
    }

}
