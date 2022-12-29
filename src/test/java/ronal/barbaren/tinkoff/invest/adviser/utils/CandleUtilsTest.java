package ronal.barbaren.tinkoff.invest.adviser.utils;

import org.junit.jupiter.api.Test;
import ronal.barbaren.tinkoff.invest.adviser.BaseTest;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ronal.barbaren.bigdecimalutils.BigDecimalUtils.is;

class CandleUtilsTest extends BaseTest {

    @Test
    void getMaxCandleByPrice_array() {
        Candle[] c = {getRandomCandle(), getRandomCandle()};
        Candle max = CandleUtils.getMaxCandleByMaxPrice(c);
        Candle expected = is(c[0].getMaxPrice()).gt(c[1].getMaxPrice()) ? c[0] : c[1];
        assertEquals(expected, max);
    }

}