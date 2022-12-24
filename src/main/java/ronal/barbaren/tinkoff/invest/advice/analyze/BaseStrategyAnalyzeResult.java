package ronal.barbaren.tinkoff.invest.advice.analyze;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.advice.step.result.TradeStrategyStepResult;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class BaseStrategyAnalyzeResult implements StrategyAnalyzeResult {
    private String name;
    private boolean skip;
    private boolean buy;
    private boolean sell;
    private BigDecimal price;
    private long lots;

    public BaseStrategyAnalyzeResult(TradeStrategyStepResult result) {
        this.name = result.getName();
        this.buy = result.isBuy();
        this.sell = result.isSell();
        this.price = result.getPrice();
        this.lots = result.getLots();
        this.skip = false;
    }
}
