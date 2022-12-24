package ronal.barbaren.tinkoff.invest.strategy;

import ronal.barbaren.tinkoff.invest.strategy.analyze.StrategyAnalyzeResult;

import javax.annotation.Nullable;

public interface Strategy {
    @Nullable
    StrategyAnalyzeResult analyze();
}
