package ronal.barbaren.tinkoff.invest.adviser.utils;

import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;
import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.FilterAdviser;
import ronal.barbaren.tinkoff.invest.adviser.SeqTradeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.TradeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.BaseTradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.TradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.result.BaseTradeAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.chain.AnyFilterChainAdviser;
import ronal.barbaren.tinkoff.invest.adviser.chain.AnyTradeChainAdviser;
import ronal.barbaren.tinkoff.invest.adviser.chain.FilterAndTradeChainAdviser;
import ronal.barbaren.tinkoff.invest.adviser.chain.SeqTradeChainAdviser;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@UtilityClass
public class AdviserUtils {

    public static TradeAdviser anyTrade(TradeAdviser... advisers) {
        return new AnyTradeChainAdviser(advisers);
    }

    public static TradeAdviser seqTrade(TradeAdviser first, SeqTradeAdviser... advisers) {
        return new SeqTradeChainAdviser(first, advisers);
    }

    public static FilterAdviser anyFilter(FilterAdviser... advisers) {
        return new AnyFilterChainAdviser(advisers);
    }

    public static TradeAdviser compose(FilterAdviser filter, TradeAdviser trade) {
        return new FilterAndTradeChainAdviser(filter, trade);
    }

    public static TradeAdvice buy(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price))
            throw new IllegalArgumentException("price can't be null");
        if (lots == 0)
            throw new IllegalArgumentException("lots can't be 0");
        return new BaseTradeAdvice(name, new BaseTradeAdviceResult(true, false, price, lots));
    }

    public static TradeAdvice sell(String name, BigDecimal price, long lots) {
        if (Objects.isNull(price))
            throw new IllegalArgumentException("price can't be null");
        if (lots == 0)
            throw new IllegalArgumentException("lots can't be 0");
        return new BaseTradeAdvice(name, new BaseTradeAdviceResult(false, true, price, lots));
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
