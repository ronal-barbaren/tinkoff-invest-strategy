package ronal.barbaren.tinkoff.invest.adviser;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.BaseTradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.TradeAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.trade.result.TradeAdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public abstract class BaseSeqTradeAdviser implements SeqTradeAdviser {
    private final String name;
    private final TradeAdvice skip;

    protected BaseSeqTradeAdviser(String name) {
        this.name = name;
        this.skip = new BaseTradeAdvice(name, null);
    }

    protected TradeAdvice buy(BigDecimal price, long lots) {
        return AdviserUtils.buy(name, price, lots);
    }

    protected TradeAdvice sell(BigDecimal price, long lots) {
        return AdviserUtils.sell(name, price, lots);
    }

    protected TradeAdvice seq(TradeAdvice advice, BigDecimal price, long lots) {
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("previous advice can't be null");
        TradeAdviceResult result = advice.getResult();
        if (Objects.isNull(result))
            throw new IllegalArgumentException("previous advice result can't be null");

        if (result.isBuy())
            return buy(price, lots);
        else if (result.isSell())
            return sell(price, lots);
        else throw new IllegalArgumentException("invalid previous advice");
    }

    protected TradeAdvice lots(TradeAdvice advice, long lots) {
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("previous advice can't be null");
        return seq(advice, advice.getResult().getPrice(), lots);
    }

    protected TradeAdvice skip() {
        return skip;
    }
}
