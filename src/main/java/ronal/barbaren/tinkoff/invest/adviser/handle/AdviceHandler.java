package ronal.barbaren.tinkoff.invest.adviser.handle;

import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;

public interface AdviceHandler {
    boolean handle(Advice advice);
}
