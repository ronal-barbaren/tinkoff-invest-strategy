package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.FilterAdviser;
import ronal.barbaren.tinkoff.invest.adviser.TradeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.BaseTradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.TradeAdvice;

import java.util.Objects;

import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.getCompositeName;

@Getter
public class FilterAndTradeChainAdviser implements TradeAdviser {
    private final String name;
    private final FilterAdviser filter;
    private final TradeAdviser trade;
    private final TradeAdvice skip;

    public FilterAndTradeChainAdviser(FilterAdviser filter, TradeAdviser trade) {
        if (Objects.isNull(filter))
            throw new IllegalArgumentException("filter adviser can't be null");
        if (Objects.isNull(trade))
            throw new IllegalArgumentException("trade adviser can't be null");
        this.filter = filter;
        this.trade = trade;
        this.name = getCompositeName(filter, trade);
        this.skip = new BaseTradeAdvice(this.name, null);
    }

    @Override
    public TradeAdvice getAdvice() {
        FilterAdvice fa = filter.getAdvice();
        if (Objects.isNull(fa))
            throw new IllegalStateException("filter advice can't be null, adviser: " + filter.getName());
        if (fa.isEnd())
            return skip;
        TradeAdvice ta = trade.getAdvice();
        if (Objects.isNull(ta))
            throw new IllegalStateException("trade advice can't be null, adviser: " + trade.getName());
        return ta;
    }
}
