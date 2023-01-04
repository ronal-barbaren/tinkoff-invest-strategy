package ronal.barbaren.tinkoff.invest.adviser.chain;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.FilterAdviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.BaseFilterAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;

import java.util.Objects;

import static ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils.getCompositeName;

@Getter
public class AnyFilterChainAdviser implements FilterAdviser {
    private final String name;
    private final FilterAdvice skip;
    private final FilterAdviser[] advisers;

    public AnyFilterChainAdviser(FilterAdviser... advisers) {
        if (Objects.isNull(advisers) || advisers.length == 0)
            throw new IllegalArgumentException("anyFilter, advisers is null or empty");
        this.name = getCompositeName(advisers);
        this.advisers = advisers;
        this.skip = new BaseFilterAdvice(this.name, false);
    }

    @Override
    public FilterAdvice getAdvice() {
        for (FilterAdviser filter : advisers) {
            FilterAdvice advise = filter.getAdvice();
            if (!advise.isEnd())
                continue;
            return advise;
        }
        return skip;
    }
}
