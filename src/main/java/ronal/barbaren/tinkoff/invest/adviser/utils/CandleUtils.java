package ronal.barbaren.tinkoff.invest.adviser.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@UtilityClass
public class CandleUtils {

    public static <T extends Candle> T getMaxCandleByMaxPrice(List<T> candles) {
        if (CollectionUtils.isEmpty(candles))
            return null;
        return getMaxCandleByMaxPrice(candles.stream());
    }

    @SafeVarargs
    public static <T extends Candle> T getMaxCandleByMaxPrice(T... candles) {
        if (Objects.isNull(candles) || candles.length == 0)
            return null;
        return getMaxCandleByMaxPrice(Arrays.stream(candles));
    }

    public static <T extends Candle> T getMinCandleByMinPrice(List<T> candles) {
        if (CollectionUtils.isEmpty(candles))
            return null;
        return getMinCandleByMinPrice(candles.stream());
    }

    @SafeVarargs
    public static <T extends Candle> T getMinCandleByMinPrice(T... candles) {
        if (Objects.isNull(candles) || candles.length == 0)
            return null;
        return getMinCandleByMinPrice(Arrays.stream(candles));
    }

    public static <T extends Candle> T getMaxCandleByMaxPrice(Stream<T> candles) {
        if (Objects.isNull(candles))
            return null;
        return candles
                .filter(Objects::nonNull)
                .filter(a -> Objects.nonNull(a.getMaxPrice()))
                .max(Comparator.comparing(Candle::getMaxPrice))
                .orElse(null);
    }

    public static <T extends Candle> T getMinCandleByMinPrice(Stream<T> candles) {
        if (Objects.isNull(candles))
            return null;
        return candles
                .filter(Objects::nonNull)
                .filter(a -> Objects.nonNull(a.getMinPrice()))
                .min(Comparator.comparing(Candle::getMinPrice))
                .orElse(null);
    }

    @Nullable
    public static BigDecimal getSubtractMaxMinPrice(Candle candle) {
        if (Objects.isNull(candle))
            return null;
        BigDecimal maxPrice = candle.getMaxPrice();
        BigDecimal minPrice = candle.getMinPrice();
        return maxPrice.subtract(minPrice);
    }
}
