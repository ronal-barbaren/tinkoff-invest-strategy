package ronal.barbaren.tinkoff.invest.adviser.base.order.trade;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.BaseTradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.TradeOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils;

import java.math.BigDecimal;

@Getter
public abstract class BaseTradeOrderAdviser implements TradeOrderAdviser {
    private final String name;
    private final TradeOrderAdvice skip;

    protected BaseTradeOrderAdviser(String name) {
        this.name = name;
        this.skip = new BaseTradeOrderAdvice(name, null);
    }

    protected TradeOrderAdvice buy(BigDecimal price, long lots) {
        return AdviserUtils.buy(name, price, lots);
    }

    protected TradeOrderAdvice sell(BigDecimal price, long lots) {
        return AdviserUtils.sell(name, price, lots);
    }

    protected TradeOrderAdvice skip() {
        return skip;
    }
}
