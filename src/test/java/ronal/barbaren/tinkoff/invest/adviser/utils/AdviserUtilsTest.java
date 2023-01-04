package ronal.barbaren.tinkoff.invest.adviser.utils;

import org.junit.jupiter.api.Test;
import ronal.barbaren.instant.utils.InstantUtils;
import ronal.barbaren.tinkoff.invest.adviser.BaseTest;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ronal.barbaren.bigdecimalutils.BigDecimalUtils.is;

class AdviserUtilsTest extends BaseTest {
    @Test
    void getAByLinearApproximation() {
        int[] y = {5, 3, 4, 2, 3, 1, 2, 1, 3, 0};
        List<Candle> candles = new ArrayList<>(y.length);
        for (int i = 0; i < y.length; i++) {
            BigDecimal price = BigDecimal.valueOf(y[i]);
            candles.add(TCandle.builder()
                    .date(InstantUtils.plusMinute(Instant.now(), i))
                    .maxPrice(price)
                    .minPrice(price)
                    .build());
        }
        BigDecimal a = MathUtils.getAByLinearApproximation(candles);
        assertNotNull(a);
        a = a.setScale(5, RoundingMode.HALF_EVEN);
        assertTrue(is(a).eq(-0.37576));
    }
}