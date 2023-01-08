package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.TradeOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.seq.SeqTradeOrderAdviser;

import javax.annotation.Nonnull;
import java.util.Objects;

import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.concatNames;
import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.getCompositeName;

@Getter
public class SeqTradeOrderChainAdviser implements TradeOrderAdviser {
    private final String name;
    private final TradeOrderAdviser first;
    private final SeqTradeOrderAdviser[] advisers;

    public SeqTradeOrderChainAdviser(TradeOrderAdviser first, SeqTradeOrderAdviser... advisers) {
        if (Objects.isNull(first))
            throw new IllegalArgumentException("first adviser can't be null");
        if (Objects.isNull(advisers) || advisers.length == 0)
            throw new IllegalArgumentException("seqTrade, advisers is null or empty");
        this.first = first;
        this.advisers = advisers;
        this.name = concatNames(first.getName(), getCompositeName(advisers));
    }

    @Nonnull
    @Override
    public TradeOrderAdvice getTrade() {
        TradeOrderAdvice advice = first.getTrade();
        if (Objects.isNull(advice.getResult()))
            return advice;
        for (SeqTradeOrderAdviser ta : advisers) {
            advice = ta.getTrade(advice);
            if (Objects.isNull(advice.getResult()))
                return advice;
        }
        return advice;
    }
}
