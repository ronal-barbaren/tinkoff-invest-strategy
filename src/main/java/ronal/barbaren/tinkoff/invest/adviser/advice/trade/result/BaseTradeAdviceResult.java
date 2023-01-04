package ronal.barbaren.tinkoff.invest.adviser.advice.trade.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class BaseTradeAdviceResult implements TradeAdviceResult {
    private final boolean buy;
    private final boolean sell;
    private final BigDecimal price;
    private final long lots;
}
