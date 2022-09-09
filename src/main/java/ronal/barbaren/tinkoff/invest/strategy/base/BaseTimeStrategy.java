package ronal.barbaren.tinkoff.invest.strategy.base;

import ronal.barbaren.instant.utils.InstantUtils;
import ronal.barbaren.tinkoff.invest.strategy.Environment;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class BaseTimeStrategy extends BaseStrategy {
    private Instant currentMonth;
    private Instant currentDay;
    private Instant currentHour;
    private Instant currentMinute;

    @Override
    public void run() {
        if (isMinuteChanged()) {
            currentMinute = now().truncatedTo(ChronoUnit.MINUTES);
            doEveryMinute();
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
    }

    protected boolean isMinuteChanged() {
        return isTimeChanged(currentMinute, ChronoUnit.MINUTES, 1);
    }

    protected boolean isHourChanged() {
        return isTimeChanged(currentHour, ChronoUnit.HOURS, 1);
    }

    protected boolean isDayChanged() {
        return isTimeChanged(currentDay, ChronoUnit.DAYS, 1);
    }

    protected boolean isMonthChanged() {
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

    protected void doEveryMinute() {
    }

    protected void doEveryHour() {
    }

    protected void doEveryDay() {
    }

    protected void doEveryMonth() {
    }

    protected Instant now() {
        return getEnv().now();
    }

    protected BaseTimeStrategy(Api api, Environment env) {
        super(api, env);
    }
}
