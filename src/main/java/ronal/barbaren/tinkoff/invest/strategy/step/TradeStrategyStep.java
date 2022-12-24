package ronal.barbaren.tinkoff.invest.strategy.step;

import ronal.barbaren.tinkoff.invest.strategy.Strategy;
import ronal.barbaren.tinkoff.invest.strategy.step.result.TradeStrategyStepResult;

public interface TradeStrategyStep<T extends Strategy, E extends TradeStrategyStepResult> {
    E run(T strategy);
}
