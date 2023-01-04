package ronal.barbaren.tinkoff.invest.adviser.advice.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseFilterAdvice implements FilterAdvice {
    private final String name;
    private final boolean end;
}
