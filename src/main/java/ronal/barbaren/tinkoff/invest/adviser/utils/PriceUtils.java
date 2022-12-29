package ronal.barbaren.tinkoff.invest.adviser.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

import static ronal.barbaren.bigdecimalutils.BigDecimalUtils.is;

@UtilityClass
public class PriceUtils {
    public static BigDecimal roundByMinIncrement(BigDecimal price, BigDecimal minIncrement) {
        BigDecimal m = is(price).divideHalfEven(minIncrement, 0);
        return is(m.longValue()).multiply(minIncrement);
    }
}
