package guru.springframework.msscbeerorderservice.statemachines;

import guru.springframework.msscbeerorderservice.domain.BeerOrderEventsEnums;
import guru.springframework.msscbeerorderservice.domain.BeerOrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
@RequiredArgsConstructor
@EnableStateMachineFactory
public class BeerOrderStateMachineConfig extends StateMachineConfigurerAdapter<BeerOrderStatusEnum, BeerOrderEventsEnums> {

    private final Action<BeerOrderStatusEnum, BeerOrderEventsEnums> validateOrderAction;
    private final Action<BeerOrderStatusEnum, BeerOrderEventsEnums> allocateOrderAction;

    @Override
    public void configure(StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventsEnums> states) throws Exception {
        states.withStates()
                .initial(BeerOrderStatusEnum.NEW)
                .states(EnumSet.allOf(BeerOrderStatusEnum.class))
                .end(BeerOrderStatusEnum.DELIVERED)
                .end(BeerOrderStatusEnum.PICKED_UP)
                .end(BeerOrderStatusEnum.ALLOCATION_EXCEPTION)
                .end(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                .end(BeerOrderStatusEnum.DELIVERY_EXCEPTION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BeerOrderStatusEnum, BeerOrderEventsEnums> transitions) throws Exception {
        transitions.withExternal()
                    .source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.VALIDATION_PENDING)
                    .event(BeerOrderEventsEnums.VALIDATE_ORDER)
                .action(validateOrderAction)
                .and().withExternal()
                    .source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.VALIDATED)
                    .event(BeerOrderEventsEnums.VALIDATION_PASSED)
                .and().withExternal()
                    .source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                    .event(BeerOrderEventsEnums.VALIDATION_FAILED)
                .and().withExternal()
                    .source(BeerOrderStatusEnum.VALIDATED).target(BeerOrderStatusEnum.ALLOCATION_PENDING)
                    .event(BeerOrderEventsEnums.VALIDATE_ORDER)
                    .action(allocateOrderAction);

    }
}
