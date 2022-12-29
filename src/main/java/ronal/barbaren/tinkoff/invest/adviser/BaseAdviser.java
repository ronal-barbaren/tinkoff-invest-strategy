package ronal.barbaren.tinkoff.invest.adviser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ronal.barbaren.tinkoff.invest.adviser.env.Environment;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

@Deprecated
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public abstract class BaseAdviser implements Adviser {
    private final Api api;
    private final Environment env;
}
