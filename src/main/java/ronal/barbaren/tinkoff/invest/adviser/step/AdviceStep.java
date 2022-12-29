package ronal.barbaren.tinkoff.invest.adviser.step;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.step.result.AdviceStepResult;

@FunctionalInterface
public interface AdviceStep<R extends AdviceStepResult, A extends Adviser<? extends AdviceStepResult, ? extends AdviceStep<?, ?>>> {
    R run(A adviser);
}
