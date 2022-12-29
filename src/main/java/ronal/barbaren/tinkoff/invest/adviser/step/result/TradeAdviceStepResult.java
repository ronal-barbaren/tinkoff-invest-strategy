package ronal.barbaren.tinkoff.invest.adviser.step.result;

import ronal.barbaren.tinkoff.invest.wrapper.dto.DtoBuySell;

import java.math.BigDecimal;

public interface TradeAdviceStepResult extends AdviceStepResult, DtoBuySell {
    BigDecimal getPrice();

    long getLots();
}
