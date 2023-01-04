package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.TradeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.BaseTradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.TradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.result.TradeAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils;

import java.util.Objects;

@Getter
public class AnyTradeChainAdviser implements TradeAdviser {
    private final String name;
    private final TradeAdvice skip;
    private final TradeAdviser[] advisers;

    public AnyTradeChainAdviser(TradeAdviser... advisers) {
        if (Objects.isNull(advisers) || advisers.length == 0)
            throw new IllegalArgumentException("anyTrade, advisers is null or empty");
        this.advisers = advisers;
        this.name = AdviserUtils.getCompositeName(advisers);
        this.skip = new BaseTradeAdvice(this.name, null);
    }

    @Override
    public TradeAdvice getAdvice() {
        for (TradeAdviser ta : advisers) {
            TradeAdvice advice = ta.getAdvice();
            if (Objects.isNull(advice))
                throw new IllegalArgumentException("advise is null, adviser: " + ta.getName());
            TradeAdviceResult result = advice.getResult();
            if (Objects.nonNull(result))
                return advice;
        }
        return skip;
    }
}
