package ronal.barbaren.tinkoff.invest.adviser.advice.trade;

import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.result.TradeAdviceResult;

public interface TradeAdvice extends Advice {
    TradeAdviceResult getResult();
}
