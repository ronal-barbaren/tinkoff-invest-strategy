package ronal.barbaren.tinkoff.invest.strategy.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static ronal.barbaren.bigdecimalutils.BigDecimalUtils.is;

@UtilityClass
public class StrategyUtils {

    @Nullable
    public static BigDecimal sigmoid(BigDecimal x, int scale) {
        if (Objects.isNull(x))
            return null;
        BigDecimal divider = x.pow(2).add(BigDecimal.ONE).sqrt(MathContext.DECIMAL64);
        return is(x).divideHalfEven(divider, scale);
    }

    @Nullable
    public static BigDecimal getAByLinearApproximation(List<Candle> candles) {
        if (CollectionUtils.isEmpty(candles))
            return null;
        int n = candles.size();
        if (n == 1)
            return null;
        Iterator<Candle> iterator = candles.iterator();
        Candle first = iterator.next();
        Instant firstDate = first.getDate();
        long sumX = 0;
        long sumXx = 0;
        long x = 0;
        BigDecimal sumY = getAvg(first);
        BigDecimal sumXy = is(sumX).multiply(sumY);
        while (iterator.hasNext()) {
            Candle c = iterator.next();
            if (firstDate.isAfter(c.getDate()))
                throw new IllegalArgumentException("incorrect candles sort");
            long incX = Duration.between(firstDate, c.getDate()).toMinutes();
            BigDecimal incY = getAvg(c);
            x += incX;
            sumX += x;
            sumXx += x * x;
            sumY = sumY.add(incY);
            sumXy = is(x).add(incY).add(sumXy);
            firstDate = c.getDate();
        }
        double top = n * sumXy.doubleValue() - sumX * sumY.doubleValue();
        long down = n * sumXx - sumX * sumX;
        if (down == 0)
            return null;
        return is(top / down).value();
    }

    private static BigDecimal getAvg(Candle candle) {
        return is(candle.getMaxPrice().add(candle.getMinPrice())).divideHalfEven(2, 5);
    }
}
