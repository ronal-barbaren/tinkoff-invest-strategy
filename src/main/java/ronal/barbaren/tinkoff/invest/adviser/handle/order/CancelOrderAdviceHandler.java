package ronal.barbaren.tinkoff.invest.adviser.handle.order;

import lombok.RequiredArgsConstructor;
import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel.CancelOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.handle.BaseAdviceHandler;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

import java.util.Objects;

@RequiredArgsConstructor
public class CancelOrderAdviceHandler extends BaseAdviceHandler<CancelOrderAdvice> {
    private final Api api;

    @Override
    public boolean handle(Advice advice) {
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("advice can't be null");
        if (advice instanceof CancelOrderAdvice order)
            return inner(order);
        return false;
    }

    @Override
    protected boolean inner(CancelOrderAdvice orderAdvice) {
        api.cancelOrder(orderAdvice.getOrderId());
        return true;
    }
}
