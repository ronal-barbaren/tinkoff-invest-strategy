package ronal.barbaren.tinkoff.invest.advice;

import ronal.barbaren.tinkoff.invest.advice.env.Environment;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class BaseTradeStrategy extends BaseCachedStrategy {

    private BigDecimal subtractMaxAndMinPriceByLastTradeDayAndCurrentDay;

    @Override
    public void doEveryMinute() {
        super.doEveryMinute();
        subtractMaxAndMinPriceByLastTradeDayAndCurrentDay = null;
    }

    @Nullable
    public BigDecimal getCurrentPriceSubtractLastOperationPrice() {
        Operation lOperation = getLastExecutedOperation();
        if (Objects.isNull(lOperation))
            return null;
        BigDecimal cPrice = getPosition().getCurrentPrice();
        BigDecimal oPrice = lOperation.getPrice();
        return cPrice.subtract(oPrice);
    }

    @Nullable
    public BigDecimal getSubtractMaxAndMinPriceByLastTradeDayAndCurrentDay() {
        if (Objects.nonNull(subtractMaxAndMinPriceByLastTradeDayAndCurrentDay))
            return subtractMaxAndMinPriceByLastTradeDayAndCurrentDay;
        Candle lCandle = getOneDayCandleByLastTradeDay();
        if (Objects.isNull(lCandle))
            return null;
        BigDecimal maxPrice = lCandle.getMaxPrice();
        BigDecimal minPrice = lCandle.getMinPrice();
        Candle cCandle = getOneDayCandleByCurrentDay();
        if (Objects.nonNull(cCandle)) {
            maxPrice = maxPrice.max(cCandle.getMaxPrice());
            minPrice = minPrice.min(cCandle.getMinPrice());
        }
        subtractMaxAndMinPriceByLastTradeDayAndCurrentDay = maxPrice.subtract(minPrice);
        return subtractMaxAndMinPriceByLastTradeDayAndCurrentDay;
    }

    protected BaseTradeStrategy(Api api, Environment env) {
        super(api, env);
    }
}
