package ronal.barbaren.tinkoff.invest.strategy.step;

import ronal.barbaren.tinkoff.invest.strategy.Strategy;
import ronal.barbaren.tinkoff.invest.strategy.step.result.BaseTradeStrategyStepResult;
import ronal.barbaren.tinkoff.invest.strategy.step.result.TradeStrategyStepResult;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BaseSeqTradeStrategyStep<T extends Strategy> implements SeqTradeStrategyStep<T, TradeStrategyStepResult> {
    protected TradeStrategyStepResult sell(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price) || lots == 0)
            throw new IllegalArgumentException("price can't be null or lots can't be 0");
        return new BaseTradeStrategyStepResult(name, false, true, price, lots);
    }

    protected TradeStrategyStepResult buy(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price) || lots == 0)
            throw new IllegalArgumentException("price can't be null or lots can't be 0");
        return new BaseTradeStrategyStepResult(name, true, false, price, lots);
    }

    protected TradeStrategyStepResult seq(String name, TradeStrategyStepResult previous, long lots, BigDecimal price) {
        if (previous.isBuy())
            return buy(name, price, lots);
        else if (previous.isSell())
            return sell(name, price, lots);
        return null;
    }
}
