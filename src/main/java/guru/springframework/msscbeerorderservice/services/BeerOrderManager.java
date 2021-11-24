package guru.springframework.msscbeerorderservice.services;

import guru.springframework.msscbeerorderservice.domain.BeerOrder;

import java.util.UUID;

public interface BeerOrderManager {

    BeerOrder newBearOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);
}
