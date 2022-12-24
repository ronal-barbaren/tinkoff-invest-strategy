package ronal.barbaren.tinkoff.invest.advice.env;

import java.time.Instant;

public class BaseEnvironment implements Environment {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
