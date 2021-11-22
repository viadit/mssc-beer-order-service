package guru.springframework.msscbeerorderservice.services;

import guru.springframework.msscbeerorderservice.web.model.BeerOrderDto;
import guru.springframework.msscbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {

    public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickUpOrder(UUID customerId, UUID orderId);
}
