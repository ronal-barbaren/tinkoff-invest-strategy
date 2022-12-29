package ronal.barbaren.tinkoff.invest.adviser.step;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.step.result.TradeAdviceStepResult;

@FunctionalInterface
public interface SeqTradeAdviceStep<S extends Adviser, P extends TradeAdviceStepResult, R extends TradeAdviceStepResult> {
    R run(S strategy, P result);
}
