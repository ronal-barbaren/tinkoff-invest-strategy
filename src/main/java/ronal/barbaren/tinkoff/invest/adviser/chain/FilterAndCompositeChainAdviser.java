package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;
import ronal.barbaren.tinkoff.invest.adviser.base.CompositeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.filter.FilterAdviser;

import javax.annotation.Nonnull;
import java.util.Objects;

import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.getCompositeName;

@Getter
public class FilterAndCompositeChainAdviser implements CompositeAdviser {
    private final String name;
    private final FilterAdviser filter;
    private final CompositeAdviser composite;

    public FilterAndCompositeChainAdviser(FilterAdviser filter, CompositeAdviser composite) {
        if (Objects.isNull(filter))
            throw new IllegalArgumentException("filter adviser can't be null");
        if (Objects.isNull(composite))
            throw new IllegalArgumentException("composite adviser can't be null");
        this.filter = filter;
        this.composite = composite;
        this.name = getCompositeName(filter, composite);
    }

    @Nonnull
    @Override
    public Advice getAdvice() {
        FilterAdvice fa = filter.getFilter();
        if (fa.isEnd())
            return fa;
        return composite.getAdvice();
    }
}
