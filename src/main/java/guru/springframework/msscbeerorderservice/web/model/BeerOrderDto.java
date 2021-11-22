package guru.springframework.msscbeerorderservice.web.model;

import guru.springframework.msscbeerorderservice.domain.BeerOrderLine;
import guru.springframework.msscbeerorderservice.domain.Customer;
import guru.springframework.msscbeerorderservice.domain.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDto extends BaseItem{

    private UUID customerId;
    private String customerRef;
    private OrderStatusEnum orderStatus;
    private List<BeerOrderLineDto> beerOrderLines;
    private String orderStatusCallbackUrl;

    @Builder
    public BeerOrderDto(UUID id, Integer version, OffsetDateTime createdDate,
                        OffsetDateTime lastModifiedDate, UUID customerId, String customerRef,
                        OrderStatusEnum orderStatus, List<BeerOrderLineDto> beerOrderLines
            , String orderStatusCallbackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.orderStatus = orderStatus;
        this.beerOrderLines = beerOrderLines;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }
}
