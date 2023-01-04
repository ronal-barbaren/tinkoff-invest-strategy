package ronal.barbaren.tinkoff.invest.adviser;

import ronal.barbaren.tinkoff.invest.adviser.advice.trade.TradeAdvice;

import javax.annotation.Nonnull;

public interface TradeAdviser extends Adviser {
    @Nonnull
    TradeAdvice getAdvice();
}
