package ronal.barbaren.tinkoff.invest.adviser;

import ronal.barbaren.tinkoff.invest.adviser.advice.filter.FilterAdvice;

import javax.annotation.Nonnull;

public interface FilterAdviser extends Adviser {
    @Nonnull
    FilterAdvice getAdvice();
}
