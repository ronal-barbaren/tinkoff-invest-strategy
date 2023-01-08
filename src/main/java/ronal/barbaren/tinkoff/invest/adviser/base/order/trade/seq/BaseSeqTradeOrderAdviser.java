package ronal.barbaren.tinkoff.invest.adviser.base.order.trade.seq;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.BaseTradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.TradeOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.trade.result.TradeOrderAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public abstract class BaseSeqTradeOrderAdviser implements SeqTradeOrderAdviser {
    private final String name;
    private final TradeOrderAdvice skip;

    protected BaseSeqTradeOrderAdviser(String name) {
        this.name = name;
        this.skip = new BaseTradeOrderAdvice(name, null);
    }

    protected TradeOrderAdvice buy(BigDecimal price, long lots) {
        return AdviserUtils.buy(name, price, lots);
    }

    protected TradeOrderAdvice sell(BigDecimal price, long lots) {
        return AdviserUtils.sell(name, price, lots);
    }

    protected TradeOrderAdvice seq(TradeOrderAdvice advice, BigDecimal price, long lots) {
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("previous advice can't be null");
        TradeOrderAdviceResult result = advice.getResult();
        if (Objects.isNull(result))
            throw new IllegalArgumentException("previous advice result can't be null");

        if (result.isBuy())
            return buy(price, lots);
        else if (result.isSell())
            return sell(price, lots);
        else throw new IllegalArgumentException("invalid previous advice");
    }

    protected TradeOrderAdvice lots(TradeOrderAdvice advice, long lots) {
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("previous advice can't be null");
        return seq(advice, advice.getResult().getPrice(), lots);
    }

    protected TradeOrderAdvice skip() {
        return skip;
    }
}
