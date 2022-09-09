package ronal.barbaren.tinkoff.invest.strategy.base;

import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.instant.utils.InstantUtils;
import ronal.barbaren.tinkoff.invest.strategy.Environment;
import ronal.barbaren.tinkoff.invest.strategy.utils.CandleUtils;
import ronal.barbaren.tinkoff.invest.strategy.utils.OperationUtils;
import ronal.barbaren.tinkoff.invest.wrapper.api.Api;
import ronal.barbaren.tinkoff.invest.wrapper.dto.amount.Amount;
import ronal.barbaren.tinkoff.invest.wrapper.dto.candle.Candle;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;
import ronal.barbaren.tinkoff.invest.wrapper.dto.order.Order;
import ronal.barbaren.tinkoff.invest.wrapper.dto.position.Position;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static ronal.barbaren.instant.utils.InstantUtils.*;

public abstract class BaseCachedStrategy extends BaseTimeStrategy {
    private Position position;
    private Operation lastExecutedOperation;
    private Set<Operation> executedOperationsByLastTradeDay;
    private Set<Operation> executedOperationsByLastTradeWeek;
    private Set<Operation> executedOperationsByLastTradeMonth;
    private Set<Amount> amounts;
    private BigDecimal lastPrice;
    private BigDecimal minPriceIncrement;
    private Set<Order> notExecutedOrders;

    private List<Candle> oneDayCandlesByLastTradeYear;
    private List<Candle> oneMinuteCandlesByLastCalendarDay;
    private Candle oneDayCandleByLastTradeDay;
    private Candle oneMinuteCandleByMaxPriceAndLastTradeDay;

    @Override
    protected void doEveryMinute() {
        position = null;
        lastExecutedOperation = null;
        executedOperationsByLastTradeDay = null;
        executedOperationsByLastTradeWeek = null;
        executedOperationsByLastTradeMonth = null;
        amounts = null;
        lastPrice = null;
        notExecutedOrders = null;
        oneMinuteCandlesByLastCalendarDay = null;
    }

    @Override
    protected void doEveryDay() {
        minPriceIncrement = null;
        oneDayCandlesByLastTradeYear = null;
    }

    protected Set<Order> getNotExecutedOrders() {
        if (Objects.isNull(notExecutedOrders))
            notExecutedOrders = getApi().getNotExecutedOrders();
        return notExecutedOrders;
    }

    @Nonnull
    protected BigDecimal getMinPriceIncrement() {
        if (Objects.isNull(minPriceIncrement))
            minPriceIncrement = getApi().getMinPriceIncrement();
        return minPriceIncrement;
    }

    protected BigDecimal getPriceByLastDate() {
        if (Objects.isNull(lastPrice))
            lastPrice = getApi().getLastPrice();
        return lastPrice;
    }

    @Nonnull
    protected Set<Amount> getAmounts() {
        if (Objects.isNull(amounts))
            amounts = getApi().getAmounts();
        return amounts;
    }

    @Nonnull
    protected Position getPosition() {
        if (Objects.isNull(position))
            position = getApi().getPosition();
        return position;
    }

    @Nonnull
    protected Set<Operation> getExecutedOperationsByLastTradeDay() {
        if (Objects.nonNull(executedOperationsByLastTradeDay))
            return executedOperationsByLastTradeDay;
        Instant lastTradeDate = getLastTradeDate();
        if (Objects.isNull(lastTradeDate))
            return Collections.emptySet();
        executedOperationsByLastTradeDay = getApi().getExecutedOperations(lastTradeDate, plusDay(lastTradeDate));
        return executedOperationsByLastTradeDay;
    }

    @Nonnull
    protected Set<Operation> getExecutedOperationsByLastTradeWeek() {
        if (Objects.nonNull(executedOperationsByLastTradeWeek))
            return executedOperationsByLastTradeWeek;
        Instant lastTradeDate = getLastTradeDate();
        if (Objects.isNull(lastTradeDate))
            return Collections.emptySet();
        Instant to = plusDay(lastTradeDate);
        Instant from = minusWeek(to);
        executedOperationsByLastTradeWeek = getApi().getExecutedOperations(from, to);
        return executedOperationsByLastTradeWeek;
    }

    @Nonnull
    protected Set<Operation> getExecutedOperationsByLastTradeMonth() {
        if (Objects.nonNull(executedOperationsByLastTradeMonth))
            return executedOperationsByLastTradeMonth;
        Instant lastTradeDate = getLastTradeDate();
        if (Objects.isNull(lastTradeDate))
            return Collections.emptySet();
        Instant to = plusDay(lastTradeDate);
        Instant from = minusMonth(to);
        executedOperationsByLastTradeMonth = getApi().getExecutedOperations(from, to);
        return executedOperationsByLastTradeMonth;
    }

    @Nullable
    protected Operation getExecutedOperationByLastDate() {
        if (Objects.nonNull(lastExecutedOperation))
            return lastExecutedOperation;
        Set<Operation> operations = getExecutedOperationsByLastTradeDay();
        if (CollectionUtils.isEmpty(operations))
            return null;
        lastExecutedOperation = OperationUtils.getLast(operations);
        return lastExecutedOperation;
    }

    @Nonnull
    protected List<Candle> getOneDayCandlesByLastTradeYear() {
        if (Objects.nonNull(oneDayCandlesByLastTradeYear))
            return oneDayCandlesByLastTradeYear;
        Instant now = getEnv().now();
        Instant to = minusDay(now, 365);
        oneDayCandlesByLastTradeYear = getApi().getCandles1Day(to, now);
        return oneDayCandlesByLastTradeYear;
    }

    @Nullable
    protected Candle getOneDayCandleByLastTradeDay() {
        if (Objects.nonNull(oneDayCandleByLastTradeDay))
            return oneDayCandleByLastTradeDay;
        List<Candle> year1DayCandles = getOneDayCandlesByLastTradeYear();
        oneDayCandleByLastTradeDay = CandleUtils.getLastCandle(year1DayCandles);
        return oneDayCandleByLastTradeDay;
    }

    @Nonnull
    protected List<Candle> getOneMinuteCandlesByLastCalendarDay() {
        if (Objects.nonNull(oneMinuteCandlesByLastCalendarDay))
            return oneMinuteCandlesByLastCalendarDay;
        Instant now = getEnv().now();
        Instant to = minusDay(now);
        oneMinuteCandlesByLastCalendarDay = getApi().getCandles1Min(now, to);
        return oneMinuteCandlesByLastCalendarDay;
    }

    @Nullable
    protected Candle getOneMinuteCandleByMaxPriceAndLastTradeDay() {
        if (Objects.nonNull(oneMinuteCandleByMaxPriceAndLastTradeDay))
            return oneMinuteCandleByMaxPriceAndLastTradeDay;
        List<Candle> candles = getOneMinuteCandlesByLastCalendarDay();
        if (CollectionUtils.isNotEmpty(candles)) {
            oneMinuteCandleByMaxPriceAndLastTradeDay = CandleUtils.getMaxCandleByMaxPrice(candles);
            return oneMinuteCandleByMaxPriceAndLastTradeDay;
        }
        Candle last = getOneDayCandleByLastTradeDay();
        if (Objects.isNull(last))
            return null;
        Instant from = InstantUtils.truncateDay(last.getDate());
        Instant to = from.plus(1, ChronoUnit.DAYS);
        List<Candle> candles1Min = getApi().getCandles1Min(from, to);
        oneMinuteCandleByMaxPriceAndLastTradeDay = CandleUtils.getMaxCandleByMaxPrice(candles1Min);
        return oneMinuteCandleByMaxPriceAndLastTradeDay;
    }

    @Nullable
    protected Instant getLastTradeDate() {
        Candle candle = getOneDayCandleByLastTradeDay();
        if (Objects.isNull(candle))
            return null;
        return InstantUtils.truncateDay(candle.getDate());
    }

    protected BaseCachedStrategy(Api api, Environment env) {
        super(api, env);
    }
}
