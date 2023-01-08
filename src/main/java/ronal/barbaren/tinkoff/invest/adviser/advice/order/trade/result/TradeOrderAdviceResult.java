package ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public interface TradeOrderAdviceResult {
    boolean isBuy();

    boolean isSell();

    @Nonnull
    BigDecimal getPrice();

    long getLots();
}
