package ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel;

import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;

public interface CancelOrderAdvice extends Advice {
    String getOrderId();
}
