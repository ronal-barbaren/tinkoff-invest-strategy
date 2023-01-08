package ronal.barbaren.tinkoff.invest.adviser.base.order.trade;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;

import javax.annotation.Nonnull;

public interface TradeOrderAdviser extends Adviser {
    @Nonnull
    TradeOrderAdvice getTrade();
}
