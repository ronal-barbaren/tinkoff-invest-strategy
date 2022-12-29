package ronal.barbaren.tinkoff.invest.adviser;

import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.result.AdviceResult;

import javax.annotation.Nullable;

public interface Adviser<R extends AdviceResult, A extends Advice<R>> {
    @Nullable
    A advise();
}
