package ronal.barbaren.tinkoff.invest.adviser.base;

import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;

import javax.annotation.Nonnull;

public interface CompositeAdviser extends Adviser {
    @Nonnull
    Advice getAdvice();
}
