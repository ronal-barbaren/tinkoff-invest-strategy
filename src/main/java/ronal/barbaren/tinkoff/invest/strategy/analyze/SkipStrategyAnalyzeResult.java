package ronal.barbaren.tinkoff.invest.strategy.analyze;

import ronal.barbaren.tinkoff.invest.strategy.step.result.StrategyStepResult;

public class SkipStrategyAnalyzeResult extends BaseStrategyAnalyzeResult {
    public SkipStrategyAnalyzeResult(StrategyStepResult result) {
        super(result.getName(), true, false, false, null, 0);
    }
}
