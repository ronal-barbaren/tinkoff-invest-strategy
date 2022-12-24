package ronal.barbaren.tinkoff.invest.advice.step;

import ronal.barbaren.tinkoff.invest.advice.Strategy;
import ronal.barbaren.tinkoff.invest.advice.step.result.StrategyStepResult;

@FunctionalInterface
public interface StrategyStep<T extends Strategy, E extends StrategyStepResult> {
    E run(T strategy);
}
