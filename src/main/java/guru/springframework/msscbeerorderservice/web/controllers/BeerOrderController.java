package guru.springframework.msscbeerorderservice.web.controllers;

import guru.springframework.msscbeerorderservice.services.BeerOrderService;
import guru.springframework.msscbeerorderservice.web.model.BeerOrderDto;
import guru.springframework.msscbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/")
public class BeerOrderController {

    private final BeerOrderService beerOrderService;

    public BeerOrderController(BeerOrderService beerOrderService) {
        this.beerOrderService = beerOrderService;
    }

    @GetMapping("orders")
    public BeerOrderPagedList listOrders(@PathVariable("customerId") UUID customerId,
                                         @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        return beerOrderService.listOrders(customerId, PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerOrderDto placeOrder(@PathVariable("customerId") UUID customerId,
                                   @RequestBody BeerOrderDto beerOrderDto) {
        return beerOrderService.placeOrder(customerId, beerOrderDto);
    }

    @GetMapping("orders/{orderId}")
    public BeerOrderDto getOrder(@PathVariable("customerId") UUID customerId,
                                 @PathVariable("orderID") UUID orderId) {
        return beerOrderService.getOrderById(customerId, orderId);
    }

    @PutMapping("orders/{orderId}/pickup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickUpOrder(@PathVariable("customerId") UUID customerId,
                            @PathVariable("orderId") UUID orderId) {
         beerOrderService.pickUpOrder(customerId, orderId);
    }
}
