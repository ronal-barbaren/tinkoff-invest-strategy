package ronal.barbaren.tinkoff.invest.strategy.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ronal.barbaren.tinkoff.invest.strategy.Environment;
import ronal.barbaren.tinkoff.invest.strategy.Strategy;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public abstract class BaseStrategy implements Strategy {
    private final Api api;
    private final Environment env;
}
