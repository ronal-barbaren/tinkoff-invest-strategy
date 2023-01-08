package ronal.barbaren.tinkoff.invest.adviser.base.order.trade.seq;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;

import javax.annotation.Nonnull;

public interface SeqTradeOrderAdviser extends Adviser {
    @Nonnull
    TradeOrderAdvice getTrade(TradeOrderAdvice advice);
}
