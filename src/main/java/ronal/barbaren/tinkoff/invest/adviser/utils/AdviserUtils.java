package ronal.barbaren.tinkoff.invest.adviser.utils;

import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;
import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel.CancelOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.BaseTradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result.BaseTradeOrderAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.base.CompositeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.filter.FilterAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.order.cancel.CancelOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.TradeOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.seq.SeqTradeOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.chain.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@UtilityClass
public class AdviserUtils {

    public static TradeOrderAdviser anyTrade(TradeOrderAdviser... advisers) {
        return new AnyTradeOrderChainAdviser(advisers);
    }

    public static TradeOrderAdviser seqTrade(TradeOrderAdviser first, SeqTradeOrderAdviser... advisers) {
        return new SeqTradeOrderChainAdviser(first, advisers);
    }

    public static FilterAdviser anyFilter(FilterAdviser... advisers) {
        return new AnyFilterChainAdviser(advisers);
    }

    public static TradeOrderAdviser compose(FilterAdviser filter, TradeOrderAdviser trade) {
        return new FilterAndTradeOrderChainAdviser(filter, trade);
    }

    public static CompositeAdviser compose(FilterAdviser filter, CompositeAdviser composite) {
        return new FilterAndCompositeChainAdviser(filter, composite);
    }

    public static CompositeAdviser compose(CancelOrderAdviser cancel, TradeOrderAdviser trade) {
        return new CancelOrTradeOrderChainAdviser(cancel, trade);
    }

    public static TradeOrderAdvice buy(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price))
            throw new IllegalArgumentException("price can't be null");
        if (lots == 0)
            throw new IllegalArgumentException("lots can't be 0");
        return new BaseTradeOrderAdvice(name, new BaseTradeOrderAdviceResult(true, false, price, lots));
    }

    public static TradeOrderAdvice sell(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price))
            throw new IllegalArgumentException("price can't be null");
        if (lots == 0)
            throw new IllegalArgumentException("lots can't be 0");
        return new BaseTradeOrderAdvice(name, new BaseTradeOrderAdviceResult(false, true, price, lots));
    }

    public static boolean isTradeOrder(Advice advice) {
        return advice instanceof TradeOrderAdvice;
    }

    public static boolean isFilter(Advice advice) {
        return advice instanceof FilterAdvice;
    }

    public static boolean isCancelOrder(Advice advice) {
        return advice instanceof CancelOrderAdvice;
    }

    public static String getCompositeName(Adviser... advisers) {
        String compositeName = Arrays.stream(advisers)
                .map(Adviser::getName)
                .reduce(AdviserUtils::concatNames)
                .orElse(null);
        if (Strings.isNullOrEmpty(compositeName))
            throw new IllegalArgumentException("incorrect advisers");
        return compositeName;
    }

    public static String concatNames(String a, String b) {
        return String.format("%s-%s", a, b);
    }
}
