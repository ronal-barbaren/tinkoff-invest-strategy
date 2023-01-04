package ronal.barbaren.tinkoff.invest.adviser;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.BaseTradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.TradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils;

import java.math.BigDecimal;

@Getter
public abstract class BaseTradeAdviser implements TradeAdviser {
    private final String name;
    private final TradeAdvice skip;

    protected BaseTradeAdviser(String name) {
        this.name = name;
        this.skip = new BaseTradeAdvice(name, null);
    }

    protected TradeAdvice buy(BigDecimal price, long lots) {
        return AdviserUtils.buy(name, price, lots);
    }

    protected TradeAdvice sell(BigDecimal price, long lots) {
        return AdviserUtils.sell(name, price, lots);
    }

    protected TradeAdvice skip() {
        return skip;
    }
}
