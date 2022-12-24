package ronal.barbaren.tinkoff.invest.advice.step.result;

import java.math.BigDecimal;

public interface TradeStrategyStepResult extends StrategyStepResult {
    boolean isBuy();

    boolean isSell();

    BigDecimal getPrice();

    long getLots();
}
