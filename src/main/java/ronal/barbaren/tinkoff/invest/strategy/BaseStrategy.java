package ronal.barbaren.tinkoff.invest.strategy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ronal.barbaren.tinkoff.invest.strategy.env.Environment;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

@Getter
@RequiredArgsConstructor
public abstract class BaseStrategy implements Strategy {
    private final Api api;
    private final Environment env;
}
