package ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseCancelOrderAdvice implements CancelOrderAdvice {
    private final String name;
    private final String orderId;
}
