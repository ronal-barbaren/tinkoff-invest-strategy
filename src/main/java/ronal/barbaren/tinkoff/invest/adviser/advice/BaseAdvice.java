package ronal.barbaren.tinkoff.invest.adviser.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.result.AdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.advice.result.BaseAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.step.result.TradeAdviceStepResult;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class BaseAdvice implements Advice<AdviceResult> {
    private final String name;
    private final BaseAdviceResult result;

    public BaseAdvice(TradeAdviceStepResult result) {
        this.name = result.getName();
        this.result = new BaseAdviceResult(result.isBuy(), result.isSell(), result.getPrice(), result.getLots());
    }
}
