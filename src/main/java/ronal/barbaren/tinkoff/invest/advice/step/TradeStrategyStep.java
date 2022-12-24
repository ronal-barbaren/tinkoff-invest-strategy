package ronal.barbaren.tinkoff.invest.advice.step;

import ronal.barbaren.tinkoff.invest.advice.Strategy;
import ronal.barbaren.tinkoff.invest.advice.step.result.TradeStrategyStepResult;

public interface TradeStrategyStep<T extends Strategy, E extends TradeStrategyStepResult> {
    E run(T strategy);
}
