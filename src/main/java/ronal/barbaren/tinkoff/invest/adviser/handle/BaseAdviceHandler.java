package ronal.barbaren.tinkoff.invest.adviser.handle;

import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;

public abstract class BaseAdviceHandler<T extends Advice> implements AdviceHandler {
    protected abstract boolean inner(T t);
}
