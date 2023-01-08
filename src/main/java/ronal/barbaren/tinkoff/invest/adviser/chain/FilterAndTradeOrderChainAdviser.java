package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.BaseTradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.base.filter.FilterAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.TradeOrderAdviser;

import javax.annotation.Nonnull;
import java.util.Objects;

import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.getCompositeName;

@Getter
public class FilterAndTradeOrderChainAdviser implements TradeOrderAdviser {
    private final String name;
    private final FilterAdviser filter;
    private final TradeOrderAdviser trade;

    public FilterAndTradeOrderChainAdviser(FilterAdviser filter, TradeOrderAdviser trade) {
        if (Objects.isNull(filter))
            throw new IllegalArgumentException("filter adviser can't be null");
        if (Objects.isNull(trade))
            throw new IllegalArgumentException("trade adviser can't be null");
        this.filter = filter;
        this.trade = trade;
        this.name = getCompositeName(filter, trade);
    }

    @Nonnull
    @Override
    public TradeOrderAdvice getTrade() {
        FilterAdvice fa = filter.getFilter();
        if (fa.isEnd())
            return new BaseTradeOrderAdvice(fa.getName(), null);
        return trade.getTrade();
    }
}
