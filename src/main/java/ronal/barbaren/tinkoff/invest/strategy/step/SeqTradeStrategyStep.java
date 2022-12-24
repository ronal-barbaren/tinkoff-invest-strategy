package ronal.barbaren.tinkoff.invest.strategy.step;

import ronal.barbaren.tinkoff.invest.strategy.Strategy;
import ronal.barbaren.tinkoff.invest.strategy.step.result.TradeStrategyStepResult;

@FunctionalInterface
public interface SeqTradeStrategyStep<S extends Strategy, P extends TradeStrategyStepResult, R extends TradeStrategyStepResult> {
    R run(S strategy, P result);
}
