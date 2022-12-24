package ronal.barbaren.tinkoff.invest.strategy.step;

import ronal.barbaren.tinkoff.invest.strategy.Strategy;
import ronal.barbaren.tinkoff.invest.strategy.step.result.StrategyStepResult;

@FunctionalInterface
public interface StrategyStep<T extends Strategy, E extends StrategyStepResult> {
    E run(T strategy);
}
