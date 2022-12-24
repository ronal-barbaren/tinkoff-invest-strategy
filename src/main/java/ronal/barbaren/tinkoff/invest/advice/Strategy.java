package ronal.barbaren.tinkoff.invest.advice;

import ronal.barbaren.tinkoff.invest.advice.analyze.StrategyAnalyzeResult;

import javax.annotation.Nullable;

public interface Strategy {
    @Nullable
    StrategyAnalyzeResult analyze();
}
