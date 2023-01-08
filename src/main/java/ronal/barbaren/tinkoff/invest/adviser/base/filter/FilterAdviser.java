package ronal.barbaren.tinkoff.invest.adviser.base.filter;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;

import javax.annotation.Nonnull;

public interface FilterAdviser extends Adviser {
    @Nonnull
    FilterAdvice getFilter();
}
