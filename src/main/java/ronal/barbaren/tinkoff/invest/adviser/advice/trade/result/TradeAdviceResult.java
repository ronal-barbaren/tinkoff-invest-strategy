package ronal.barbaren.tinkoff.invest.adviser.advice.trade.result;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public interface TradeAdviceResult {
    boolean isBuy();

    boolean isSell();

    @Nonnull
    BigDecimal getPrice();

    long getLots();
}
