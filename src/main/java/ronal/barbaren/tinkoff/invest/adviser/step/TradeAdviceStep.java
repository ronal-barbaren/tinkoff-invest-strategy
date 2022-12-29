package ronal.barbaren.tinkoff.invest.adviser.step;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.step.result.TradeAdviceStepResult;

public interface TradeAdviceStep<T extends Adviser, E extends TradeAdviceStepResult> {
    E run(T strategy);
}
