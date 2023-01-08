package ronal.barbaren.tinkoff.invest.adviser.handle.order;

import lombok.RequiredArgsConstructor;
import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result.TradeOrderAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.handle.BaseAdviceHandler;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

import java.util.Objects;

@RequiredArgsConstructor
public class TradeOrderAdviceHandler extends BaseAdviceHandler<TradeOrderAdvice> {
    private final Api api;

    @Override
    public boolean handle(Advice advice) {
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("advice can't be null");
        if (advice instanceof TradeOrderAdvice trade)
            return inner(trade);
        return false;
    }

    @Override
    protected boolean inner(TradeOrderAdvice order) {
        TradeOrderAdviceResult result = order.getResult();
        if (Objects.isNull(result))
            return false;
        if (result.isBuy()) {
            api.buy(result.getLots(), result.getPrice());
            return true;
        } else if (result.isSell()) {
            api.sell(result.getLots(), result.getPrice());
            return true;
        }
        return false;
    }
}
