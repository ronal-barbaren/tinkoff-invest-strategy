package ronal.barbaren.tinkoff.invest.adviser.handle.filter;

import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;
import ronal.barbaren.tinkoff.invest.adviser.handle.BaseAdviceHandler;

import java.util.Objects;

public class FilterAdviceHandler extends BaseAdviceHandler<FilterAdvice> {
    @Override
    public boolean handle(Advice advice) {
        if (Objects.isNull(advice))
            throw new IllegalArgumentException("advice can't be null");
        if (advice instanceof FilterAdvice filter)
            return inner(filter);
        return false;
    }

    @Override
    protected boolean inner(FilterAdvice advice) {
        return advice.isEnd();
    }
}
