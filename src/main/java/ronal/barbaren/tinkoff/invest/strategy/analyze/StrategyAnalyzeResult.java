package ronal.barbaren.tinkoff.invest.strategy.analyze;

import java.math.BigDecimal;

public interface StrategyAnalyzeResult {
    String getName();

    boolean isSkip();

    boolean isBuy();

    boolean isSell();

    BigDecimal getPrice();

    long getLots();
}
