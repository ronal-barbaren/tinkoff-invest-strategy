package ronal.barbaren.tinkoff.invest.strategy.env;

import java.time.Instant;

public class BaseEnvironment implements Environment {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
