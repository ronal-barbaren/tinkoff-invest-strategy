package ronal.barbaren.tinkoff.invest.adviser.advice.order.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result.BaseTradeOrderAdviceResult;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class BaseTradeOrderAdvice implements TradeOrderAdvice {
    private final String name;
    private final BaseTradeOrderAdviceResult result;

    public BaseTradeOrderAdvice(String name, boolean buy, boolean sell, BigDecimal price, long lots) {
        this.name = name;
        this.result = new BaseTradeOrderAdviceResult(buy, sell, price, lots);
    }
}
