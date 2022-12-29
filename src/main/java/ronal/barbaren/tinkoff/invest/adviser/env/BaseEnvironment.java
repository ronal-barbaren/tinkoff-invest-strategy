package ronal.barbaren.tinkoff.invest.adviser.env;

import java.time.Instant;

@Deprecated
public class BaseEnvironment implements Environment {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
