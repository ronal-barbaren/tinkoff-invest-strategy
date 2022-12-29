package ronal.barbaren.tinkoff.invest.adviser.step.result;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BaseTradeAdviceStepResult extends BaseAdviceStepResult implements TradeAdviceStepResult {
    private final boolean buy;
    private final boolean sell;
    private final BigDecimal price;
    private final long lots;

    public BaseTradeAdviceStepResult(String name, boolean buy, boolean sell, BigDecimal price, long lots) {
        super(name);
        this.buy = buy;
        this.sell = sell;
        this.price = price;
        this.lots = lots;
    }
}
