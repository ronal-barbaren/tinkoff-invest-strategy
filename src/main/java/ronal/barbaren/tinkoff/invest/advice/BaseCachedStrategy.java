package ronal.barbaren.tinkoff.invest.advice;

import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.advice.env.Environment;
import ronal.barbaren.tinkoff.invest.advice.utils.CandleUtils;
import ronal.barbaren.tinkoff.invest.advice.utils.OperationUtils;
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
    private Operation lastExecutedBuyOperation;
    private Operation lastExecutedSellOperation;
    private Set<Operation> executedOperationsByCurrentDay;
    private Set<Operation> executedOperationsByLastTradeDay;
    private Set<Operation> executedOperationsByLastTradeWeek;
    private Set<Operation> executedOperationsByLastTradeMonth;
    private Set<Amount> amounts;
    private BigDecimal lastPrice;
    private BigDecimal minPriceIncrement;
    private Set<Order> notExecutedOrders;
    private List<Candle> oneDayCandlesByLastTradeYear;
    private List<Candle> oneMinuteCandlesByLastCalendarDay;
    private List<Candle> oneMinuteCandlesByLastHour;
    private List<Candle> oneMinuteCandlesByLastFiveMinute;
    private List<Candle> oneMinuteCandlesByLastTenMinute;
    private List<Candle> oneMinuteCandlesByLastTwentyMinute;
    private Candle oneDayCandleByLastTradeDay;
    private Candle oneDayCandleByCurrentDay;
    private Candle oneMinuteCandleByMaxPriceAndLastTradeDay;
    private Candle oneMinuteCandleByPrevMinute;

    @Override
    public void doEveryMinute() {
        position = null;
        lastExecutedOperation = null;
        executedOperationsByLastTradeDay = null;
        executedOperationsByLastTradeWeek = null;
        executedOperationsByLastTradeMonth = null;
        amounts = null;
        lastPrice = null;
        notExecutedOrders = null;
        oneMinuteCandlesByLastCalendarDay = null;
        oneDayCandleByCurrentDay = null;
        executedOperationsByCurrentDay = null;
        lastExecutedSellOperation = null;
        lastExecutedBuyOperation = null;
        oneMinuteCandlesByLastFiveMinute = null;
        oneMinuteCandlesByLastTenMinute = null;
        oneMinuteCandlesByLastTwentyMinute = null;
        oneMinuteCandleByPrevMinute = null;
    }

    @Override
    public void doEveryFiveMinute() {
        oneMinuteCandlesByLastHour = null;
    }

    @Override
    public void doEveryDay() {
        minPriceIncrement = null;
        oneDayCandlesByLastTradeYear = null;
        oneDayCandleByLastTradeDay = null;
    }

    public Set<Order> getNotExecutedOrders() {
        if (Objects.isNull(notExecutedOrders))
            notExecutedOrders = getApi().getNotExecutedOrders();
        return notExecutedOrders;
    }

    @Nonnull
    public BigDecimal getMinPriceIncrement() {
        if (Objects.isNull(minPriceIncrement))
            minPriceIncrement = getApi().getMinPriceIncrement();
        return minPriceIncrement;
    }

    public BigDecimal getLastPrice() {
        if (Objects.isNull(lastPrice))
            lastPrice = getApi().getLastPrice();
        return lastPrice;
    }

    @Nonnull
    public Set<Amount> getAmounts() {
        if (Objects.isNull(amounts))
            amounts = getApi().getAmounts();
        return amounts;
    }

    @Nonnull
    public Position getPosition() {
        if (Objects.isNull(position))
            position = getApi().getPosition();
        return position;
    }

    public Set<Operation> getExecutedOperationsByCurrentDay() {
        if (Objects.nonNull(executedOperationsByCurrentDay))
            return executedOperationsByCurrentDay;
        Candle cCandle = getOneDayCandleByCurrentDay();
        if (Objects.isNull(cCandle))
            return Collections.emptySet();
        Instant from = cCandle.getDate();
        Instant to = plusDay(from);
        executedOperationsByCurrentDay = getApi().getExecutedOperations(from, to);
        return executedOperationsByCurrentDay;
    }

    @Nonnull
    public Set<Operation> getExecutedOperationsByLastTradeDay() {
        if (Objects.nonNull(executedOperationsByLastTradeDay))
            return executedOperationsByLastTradeDay;
        Instant lastTradeDate = getLastTradeDate();
        if (Objects.isNull(lastTradeDate))
            return Collections.emptySet();
        executedOperationsByLastTradeDay = getApi().getExecutedOperations(lastTradeDate, plusDay(lastTradeDate));
        return executedOperationsByLastTradeDay;
    }

    @Nonnull
    public Set<Operation> getExecutedOperationsByLastTradeWeek() {
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
    public Set<Operation> getExecutedOperationsByLastTradeMonth() {
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
    public Operation getLastExecutedBuyOperation() {
        if (Objects.nonNull(lastExecutedBuyOperation))
            return lastExecutedBuyOperation;
        Set<Operation> cOperations = getExecutedOperationsByCurrentDay();
        if (!CollectionUtils.isEmpty(cOperations)) {
            lastExecutedBuyOperation = OperationUtils.getLastBuy(cOperations);
            return lastExecutedBuyOperation;
        }
        Set<Operation> lOperations = getExecutedOperationsByLastTradeDay();
        if (!CollectionUtils.isEmpty(lOperations)) {
            lastExecutedBuyOperation = OperationUtils.getLastBuy(lOperations);
            return lastExecutedBuyOperation;
        }
        return null;
    }

    @Nullable
    public Operation getLastExecutedSellOperation() {
        if (Objects.nonNull(lastExecutedSellOperation))
            return lastExecutedSellOperation;
        Set<Operation> cOperations = getExecutedOperationsByCurrentDay();
        if (!CollectionUtils.isEmpty(cOperations)) {
            lastExecutedSellOperation = OperationUtils.getLastSell(cOperations);
            return lastExecutedSellOperation;
        }
        Set<Operation> lOperations = getExecutedOperationsByLastTradeDay();
        if (!CollectionUtils.isEmpty(lOperations)) {
            lastExecutedSellOperation = OperationUtils.getLastSell(lOperations);
            return lastExecutedSellOperation;
        }
        return null;
    }

    @Nullable
    public Operation getLastExecutedOperation() {
        if (Objects.nonNull(lastExecutedOperation))
            return lastExecutedOperation;
        Set<Operation> cOperations = getExecutedOperationsByCurrentDay();
        if (!CollectionUtils.isEmpty(cOperations)) {
            lastExecutedOperation = OperationUtils.getLast(cOperations);
            return lastExecutedOperation;
        }
        Set<Operation> lOperations = getExecutedOperationsByLastTradeDay();
        if (!CollectionUtils.isEmpty(lOperations)) {
            lastExecutedOperation = OperationUtils.getLast(lOperations);
            return lastExecutedOperation;
        }
        return null;
    }

    @Nonnull
    public List<Candle> getOneDayCandlesByLastTradeYear() {
        if (Objects.nonNull(oneDayCandlesByLastTradeYear))
            return oneDayCandlesByLastTradeYear;
        Instant now = getEnv().now();
        Instant to = minusDay(now, 365);
        oneDayCandlesByLastTradeYear = getApi().getCandles1Day(to, now);
        return oneDayCandlesByLastTradeYear;
    }

    @Nullable
    public Candle getOneDayCandleByLastTradeDay() {
        if (Objects.nonNull(oneDayCandleByLastTradeDay))
            return oneDayCandleByLastTradeDay;
        List<Candle> year1DayCandles = getOneDayCandlesByLastTradeYear();
        oneDayCandleByLastTradeDay = CandleUtils.getLastCandle(year1DayCandles);
        return oneDayCandleByLastTradeDay;
    }

    @Nullable
    public Candle getOneDayCandleByCurrentDay() {
        if (Objects.nonNull(oneDayCandleByCurrentDay))
            return oneDayCandleByCurrentDay;
        Instant to = truncateDay(plusDay(now()));
        Instant from = minusDay(to, 1);
        List<Candle> candles1Day = getApi().getCandles1Day(from, to);
        if (CollectionUtils.isEmpty(candles1Day))
            return null;
        oneDayCandleByCurrentDay = CandleUtils.getLastCandle(candles1Day);
        return oneDayCandleByCurrentDay;
    }

    @Nullable
    public Candle getOneMinuteCandleByPrevMinute() {
        if (Objects.nonNull(oneMinuteCandleByPrevMinute))
            return oneMinuteCandleByPrevMinute;
        Instant to = truncateMinute(now());
        Instant from = minusMinute(to);
        List<Candle> oneCandle = getApi().getCandles1Min(from, to);
        if (CollectionUtils.isEmpty(oneCandle))
            return null;
        if (oneCandle.size() > 1)
            throw new IllegalStateException("prev minute candle must be one");
        oneMinuteCandleByPrevMinute = oneCandle.get(0);
        return oneMinuteCandleByPrevMinute;
    }

    @Nonnull
    public List<Candle> getOneMinuteCandlesByLastCalendarDay() {
        if (Objects.nonNull(oneMinuteCandlesByLastCalendarDay))
            return oneMinuteCandlesByLastCalendarDay;
        Instant to = getEnv().now();
        Instant from = minusDay(to);
        oneMinuteCandlesByLastCalendarDay = getApi().getCandles1Min(from, to);
        return oneMinuteCandlesByLastCalendarDay;
    }

    @Nonnull
    public List<Candle> getOneMinuteCandlesByLastHour() {
        if (Objects.nonNull(oneMinuteCandlesByLastHour))
            return oneMinuteCandlesByLastHour;
        Instant to = getEnv().now();
        Instant from = minusHour(to, 1);
        oneMinuteCandlesByLastHour = getApi().getCandles1Min(from, to);
        return oneMinuteCandlesByLastHour;
    }

    @Nonnull
    public List<Candle> getOneMinuteCandlesByLastFiveMinute() {
        if (Objects.nonNull(oneMinuteCandlesByLastFiveMinute))
            return oneMinuteCandlesByLastFiveMinute;
        Instant to = getEnv().now();
        Instant from = minusMinute(to, 5);
        oneMinuteCandlesByLastFiveMinute = getApi().getCandles1Min(from, to);
        return oneMinuteCandlesByLastFiveMinute;
    }

    @Nonnull
    public List<Candle> getOneMinuteCandlesByLastTenMinute() {
        if (Objects.nonNull(oneMinuteCandlesByLastTenMinute))
            return oneMinuteCandlesByLastTenMinute;
        Instant to = getEnv().now();
        Instant from = minusMinute(to, 10);
        oneMinuteCandlesByLastTenMinute = getApi().getCandles1Min(from, to);
        return oneMinuteCandlesByLastTenMinute;
    }

    @Nonnull
    public List<Candle> getOneMinuteCandlesByLastTwentyMinute() {
        if (Objects.nonNull(oneMinuteCandlesByLastTwentyMinute))
            return oneMinuteCandlesByLastTwentyMinute;
        Instant to = getEnv().now();
        Instant from = minusMinute(to, 20);
        oneMinuteCandlesByLastTwentyMinute = getApi().getCandles1Min(from, to);
        return oneMinuteCandlesByLastTwentyMinute;
    }

    @Nullable
    public Candle getOneMinuteCandleByMaxPriceAndLastTradeDay() {
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
        Instant from = truncateDay(last.getDate());
        Instant to = from.plus(1, ChronoUnit.DAYS);
        List<Candle> candles1Min = getApi().getCandles1Min(from, to);
        oneMinuteCandleByMaxPriceAndLastTradeDay = CandleUtils.getMaxCandleByMaxPrice(candles1Min);
        return oneMinuteCandleByMaxPriceAndLastTradeDay;
    }

    @Nullable
    public Instant getLastTradeDate() {
        Candle lCandle = getOneDayCandleByLastTradeDay();
        if (Objects.nonNull(lCandle))
            return truncateDay(lCandle.getDate());
        return null;
    }

    public BaseCachedStrategy(Api api, Environment env) {
        super(api, env);
    }
}
