package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.TradeOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.BaseTradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result.TradeOrderAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils;

import javax.annotation.Nonnull;
import java.util.Objects;

import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.getCompositeName;

@Getter
public class AnyTradeOrderChainAdviser implements TradeOrderAdviser {
    private final String name;
    private final TradeOrderAdvice skip;
    private final TradeOrderAdviser[] advisers;

    public AnyTradeOrderChainAdviser(TradeOrderAdviser... advisers) {
        if (Objects.isNull(advisers) || advisers.length == 0)
            throw new IllegalArgumentException("anyTrade, advisers is null or empty");
        this.advisers = advisers;
        this.name = getCompositeName(advisers);
        this.skip = new BaseTradeOrderAdvice(this.name, null);
    }

    @Nonnull
    @Override
    public TradeOrderAdvice getTrade() {
        for (TradeOrderAdviser ta : advisers) {
            TradeOrderAdvice advice = ta.getTrade();
            TradeOrderAdviceResult result = advice.getResult();
            if (Objects.nonNull(result))
                return advice;
        }
        return skip;
    }
}
