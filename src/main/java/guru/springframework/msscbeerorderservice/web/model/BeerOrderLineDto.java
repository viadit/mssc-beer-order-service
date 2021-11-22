package guru.springframework.msscbeerorderservice.web.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem{

    private UUID beerId;
    private String beerName;
    private String upc;
    private Integer orderQuantity = 0;

    @Builder
    public BeerOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate,
                            OffsetDateTime lastModifiedDate, UUID beerId, String beerName,
                            String upc, Integer orderQuantity) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.beerName = beerName;
        this.upc = upc;
        this.orderQuantity = orderQuantity;
    }
}
