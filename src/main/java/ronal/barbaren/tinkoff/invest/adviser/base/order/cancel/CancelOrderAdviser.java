package ronal.barbaren.tinkoff.invest.adviser.base.order.cancel;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel.CancelOrderAdvice;

import javax.annotation.Nonnull;

public interface CancelOrderAdviser extends Adviser {
    @Nonnull
    CancelOrderAdvice getCloseOrder();
}
