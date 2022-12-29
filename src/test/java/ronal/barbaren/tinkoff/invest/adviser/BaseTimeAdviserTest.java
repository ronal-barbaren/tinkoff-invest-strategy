package ronal.barbaren.tinkoff.invest.adviser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import ronal.barbaren.tinkoff.invest.adviser.env.Environment;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

class BaseTimeAdviserTest {
    private final Environment env = mock(Environment.class);
    private final BaseTimeAdviser strategy = mock(BaseTimeAdviser.class, InvocationOnMock::callRealMethod);

    @BeforeEach
    void setUp() {
        when(strategy.getEnv()).thenReturn(env);
    }

    @Test
    void doEveryMinute() {
        Instant now = Instant.now();
        List<Instant> seconds = IntStream.range(0, 6000)
                .mapToObj(i -> now.minus(i, ChronoUnit.SECONDS))
                .sorted()
                .toList();
        for (Instant s : seconds) {
            when(env.now()).thenReturn(s);
            strategy.analyze();
        }
        verify(strategy, times(101)).doEveryMinute();
    }

    @Test
    void doEveryFiveMinute() {
        Instant now = Instant.now();
        List<Instant> seconds = IntStream.range(0, 6000)
                .mapToObj(i -> now.minus(i, ChronoUnit.SECONDS))
                .sorted()
                .toList();
        for (Instant s : seconds) {
            when(env.now()).thenReturn(s);
            strategy.analyze();
        }
        verify(strategy, times(21)).doEveryFiveMinute();
    }

    @Test
    void doEveryHour() {
        Instant now = Instant.now();
        List<Instant> minutes = IntStream.range(0, 6000)
                .mapToObj(i -> now.minus(i, ChronoUnit.MINUTES))
                .sorted()
                .toList();
        for (Instant m : minutes) {
            when(env.now()).thenReturn(m);
            strategy.analyze();
        }
        verify(strategy, times(101)).doEveryHour();
    }

    @Test
    void doEveryDay() {
        Instant now = Instant.now();
        List<Instant> hours = IntStream.range(0, 48)
                .mapToObj(i -> now.minus(i, ChronoUnit.HOURS))
                .sorted()
                .toList();
        for (Instant h : hours) {
            when(env.now()).thenReturn(h);
            strategy.analyze();
        }
        verify(strategy, times(3)).doEveryDay();
    }

    @Test
    void doEveryMonth() {
        Instant now = Instant.parse("2007-12-03T10:15:30.00Z");
        List<Instant> days = IntStream.range(0, 50)
                .mapToObj(i -> now.minus(i, ChronoUnit.DAYS))
                .sorted()
                .toList();
        for (Instant d : days) {
            when(env.now()).thenReturn(d);
            strategy.analyze();
        }
        verify(strategy, times(3)).doEveryMonth();
    }

}