package ronal.barbaren.tinkoff.invest.adviser;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.BaseFilterAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;

@Getter
public abstract class BaseFilterAdviser implements FilterAdviser {
    private final String name;
    private final FilterAdvice end;
    private final FilterAdvice next;

    protected BaseFilterAdviser(String name) {
        this.name = name;
        end = new BaseFilterAdvice(name, true);
        next = new BaseFilterAdvice(name, false);
    }

    protected FilterAdvice end() {
        return end;
    }

    protected FilterAdvice next() {
        return next;
    }
}
