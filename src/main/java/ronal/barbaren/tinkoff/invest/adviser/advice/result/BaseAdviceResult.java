package ronal.barbaren.tinkoff.invest.adviser.advice.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class BaseAdviceResult implements AdviceResult {
    private final boolean buy;
    private final boolean sell;
    private final BigDecimal price;
    private final long lots;
}
