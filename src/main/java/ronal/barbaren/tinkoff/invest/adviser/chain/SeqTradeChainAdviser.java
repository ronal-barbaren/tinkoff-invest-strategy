package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.SeqTradeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.TradeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.BaseTradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.TradeAdvice;

import java.util.Objects;

import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.concatNames;
import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.getCompositeName;

@Getter
public class SeqTradeChainAdviser implements TradeAdviser {
    private final String name;
    private final TradeAdvice skip;
    private final TradeAdviser first;
    private final SeqTradeAdviser[] advisers;

    public SeqTradeChainAdviser(TradeAdviser first, SeqTradeAdviser... advisers) {
        if (Objects.isNull(first))
            throw new IllegalArgumentException("first adviser can't be null");
        if (Objects.isNull(advisers) || advisers.length == 0)
            throw new IllegalArgumentException("seqTrade, advisers is null or empty");
        this.first = first;
        this.advisers = advisers;
        this.name = concatNames(first.getName(), getCompositeName(advisers));
        this.skip = new BaseTradeAdvice(this.name, null);
    }

    @Override
    public TradeAdvice getAdvice() {
        TradeAdvice advice = first.getAdvice();
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("first adviser return null, adviser: " + first.getName());
        if (Objects.isNull(advice.getResult()))
            return advice;
        for (SeqTradeAdviser ta : advisers) {
            advice = ta.getAdvice(advice);
            if (Objects.isNull(advice))
                throw new IllegalArgumentException("seq advise is null, adviser: " + ta.getName());
            if (Objects.isNull(advice.getResult()))
                return advice;
        }
        return advice;
    }
}
