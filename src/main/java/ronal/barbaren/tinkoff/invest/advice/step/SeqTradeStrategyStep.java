package ronal.barbaren.tinkoff.invest.advice.step;

import ronal.barbaren.tinkoff.invest.advice.Strategy;
import ronal.barbaren.tinkoff.invest.advice.step.result.TradeStrategyStepResult;

@FunctionalInterface
public interface SeqTradeStrategyStep<S extends Strategy, P extends TradeStrategyStepResult, R extends TradeStrategyStepResult> {
    R run(S strategy, P result);
}
