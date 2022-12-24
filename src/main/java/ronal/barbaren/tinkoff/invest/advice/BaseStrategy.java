package ronal.barbaren.tinkoff.invest.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ronal.barbaren.tinkoff.invest.advice.env.Environment;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

@Getter
@RequiredArgsConstructor
public abstract class BaseStrategy implements Strategy {
    private final Api api;
    private final Environment env;
}
