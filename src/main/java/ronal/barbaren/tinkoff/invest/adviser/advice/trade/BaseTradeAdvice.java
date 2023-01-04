package ronal.barbaren.tinkoff.invest.adviser.advice.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.result.BaseTradeAdviceResult;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class BaseTradeAdvice implements TradeAdvice {
    private final String name;
    private final BaseTradeAdviceResult result;

    public BaseTradeAdvice(String name, boolean buy, boolean sell, BigDecimal price, long lots) {
        this.name = name;
        this.result = new BaseTradeAdviceResult(buy, sell, price, lots);
    }
}
