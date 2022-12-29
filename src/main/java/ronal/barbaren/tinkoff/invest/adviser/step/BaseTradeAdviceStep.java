package ronal.barbaren.tinkoff.invest.adviser.step;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.step.result.BaseTradeAdviceStepResult;
import ronal.barbaren.tinkoff.invest.adviser.step.result.TradeAdviceStepResult;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BaseTradeAdviceStep<T extends Adviser> implements TradeAdviceStep<T, TradeAdviceStepResult> {
    protected TradeAdviceStepResult sell(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price) || lots == 0)
            throw new IllegalArgumentException("price can't be null or lots can't be 0");
        return new BaseTradeAdviceStepResult(name, false, true, price, lots);
    }

    protected TradeAdviceStepResult buy(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price) || lots == 0)
            throw new IllegalArgumentException("price can't be null or lots can't be 0");
        return new BaseTradeAdviceStepResult(name, true, false, price, lots);
    }
}
