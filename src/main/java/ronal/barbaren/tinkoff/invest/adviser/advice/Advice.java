package ronal.barbaren.tinkoff.invest.adviser.advice;

import ronal.barbaren.tinkoff.invest.adviser.advice.result.AdviceResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Advice<T extends AdviceResult> {
    @Nonnull
    String getName();

    @Nullable
    T getResult();
}
