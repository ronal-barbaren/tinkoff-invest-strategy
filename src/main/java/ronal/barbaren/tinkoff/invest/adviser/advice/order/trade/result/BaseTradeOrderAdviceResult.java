package ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class BaseTradeOrderAdviceResult implements TradeOrderAdviceResult {
    private final boolean buy;
    private final boolean sell;
    private final BigDecimal price;
    private final long lots;
}
