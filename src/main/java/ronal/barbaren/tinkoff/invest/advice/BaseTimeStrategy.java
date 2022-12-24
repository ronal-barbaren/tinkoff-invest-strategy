package ronal.barbaren.tinkoff.invest.advice;

import ronal.barbaren.instant.utils.InstantUtils;
import ronal.barbaren.tinkoff.invest.advice.analyze.StrategyAnalyzeResult;
import ronal.barbaren.tinkoff.invest.advice.env.Environment;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class BaseTimeStrategy extends BaseStrategy {
    private Instant currentMonth;
    private Instant currentDay;
    private Instant currentHour;
    private Instant currentMinute;
    private Instant currentFiveMinute;

    @Override
    @Nullable
    public StrategyAnalyzeResult analyze() {
        if (isMinuteChanged()) {
            currentMinute = now().truncatedTo(ChronoUnit.MINUTES);
            doEveryMinute();
        }
        if (isFiveMinuteChanged()) {
            currentFiveMinute = now().truncatedTo(ChronoUnit.MINUTES);
            doEveryFiveMinute();
        }
        if (isHourChanged()) {
            currentHour = now().truncatedTo(ChronoUnit.HOURS);
            doEveryHour();
        }
        if (isDayChanged()) {
            currentDay = now().truncatedTo(ChronoUnit.DAYS);
            doEveryDay();
        }
        if (isMonthChanged()) {
            currentMonth = InstantUtils.truncateMonth(now());
            doEveryMonth();
        }
        return null;
    }

    public boolean isFiveMinuteChanged() {
        return isTimeChanged(currentFiveMinute, ChronoUnit.MINUTES, 5);
    }

    public boolean isMinuteChanged() {
        return isTimeChanged(currentMinute, ChronoUnit.MINUTES, 1);
    }

    public boolean isHourChanged() {
        return isTimeChanged(currentHour, ChronoUnit.HOURS, 1);
    }

    public boolean isDayChanged() {
        return isTimeChanged(currentDay, ChronoUnit.DAYS, 1);
    }

    public boolean isMonthChanged() {
        return isMonthChanged(currentMonth, 1);
    }

    private boolean isTimeChanged(Instant time, ChronoUnit period, int periods) {
        if (Objects.isNull(time))
            return true;
        Instant now = now().truncatedTo(period);
        return !now.minus(periods, period).isBefore(time);
    }

    private boolean isMonthChanged(Instant time, int periods) {
        if (Objects.isNull(time))
            return true;
        Instant now = InstantUtils.truncateMonth(now());
        return !InstantUtils.minusMonth(now, periods).isBefore(time);
    }

    public void doEveryMinute() {
    }

    public void doEveryFiveMinute() {
    }

    public void doEveryHour() {
    }

    public void doEveryDay() {
    }

    public void doEveryMonth() {
    }

    public Instant now() {
        return getEnv().now();
    }

    protected BaseTimeStrategy(Api api, Environment env) {
        super(api, env);
    }
}
