package ronal.barbaren.tinkoff.invest.adviser.advice.result;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public interface AdviceResult {
    boolean isBuy();

    boolean isSell();

    @Nonnull
    BigDecimal getPrice();

    long getLots();
}
