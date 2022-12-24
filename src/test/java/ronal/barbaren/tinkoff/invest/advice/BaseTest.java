package ronal.barbaren.tinkoff.invest.advice;

import lombok.Builder;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Random;

public abstract class BaseTest {
    private static final Random RANDOM = new Random();

    @Builder
    @Getter
    protected static class TCandle implements Candle {
        private Instant date;
        private BigDecimal minPrice;
        private BigDecimal maxPrice;
        private BigDecimal openPrice;
        private BigDecimal closePrice;
        private long lots;
    }

    protected Candle getRandomCandle() {
        return TCandle.builder()
                .date(Instant.ofEpochMilli(RANDOM.nextLong()))
                .lots(RANDOM.nextLong())
                .minPrice(BigDecimal.valueOf(RANDOM.nextDouble()))
                .maxPrice(BigDecimal.valueOf(RANDOM.nextDouble()))
                .openPrice(BigDecimal.valueOf(RANDOM.nextDouble()))
                .closePrice(BigDecimal.valueOf(RANDOM.nextDouble()))
                .build();
    }


}
