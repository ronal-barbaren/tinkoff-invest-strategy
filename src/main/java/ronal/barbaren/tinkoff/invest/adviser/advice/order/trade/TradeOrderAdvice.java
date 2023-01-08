package ronal.barbaren.tinkoff.invest.adviser.advice.order.trade;

import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result.TradeOrderAdviceResult;

public interface TradeOrderAdvice extends Advice {
    TradeOrderAdviceResult getResult();
}
