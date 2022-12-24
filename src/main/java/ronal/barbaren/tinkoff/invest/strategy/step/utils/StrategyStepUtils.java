package ronal.barbaren.tinkoff.invest.strategy.step.utils;

import lombok.experimental.UtilityClass;
import ronal.barbaren.tinkoff.invest.strategy.Strategy;
import ronal.barbaren.tinkoff.invest.strategy.step.SeqTradeStrategyStep;
import ronal.barbaren.tinkoff.invest.strategy.step.StrategyStep;
import ronal.barbaren.tinkoff.invest.strategy.step.TradeStrategyStep;
import ronal.barbaren.tinkoff.invest.strategy.step.result.StrategyStepResult;
import ronal.barbaren.tinkoff.invest.strategy.step.result.TradeStrategyStepResult;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class StrategyStepUtils {
    @Nullable
    public static <T extends Strategy> StrategyStepResult anyFilter(T strategy, List<StrategyStep<T, ? extends StrategyStepResult>> filters) {
        if (Objects.isNull(filters) || filters.isEmpty())
            throw new IllegalArgumentException("filters can't be null or empty");
        for (StrategyStep<T, ? extends StrategyStepResult> filter : filters) {
            StrategyStepResult result = filter.run(strategy);
            if (Objects.nonNull(result))
                return result;
        }
        return null;
    }

    @Nullable
    public static <T extends Strategy, E extends TradeStrategyStep<T, ? extends TradeStrategyStepResult>> TradeStrategyStepResult anyTrade(T strategy, List<E> trades) {
        if (Objects.isNull(trades) || trades.isEmpty())
            throw new IllegalArgumentException("trades can't be null or empty");
        for (E filter : trades) {
            TradeStrategyStepResult result = filter.run(strategy);
            if (Objects.nonNull(result))
                return result;
        }
        return null;
    }

    @Nullable
    public static TradeStrategyStepResult seqTrade(
            Strategy strategy,
            TradeStrategyStep<? super Strategy, ? super TradeStrategyStepResult> first,
            List<? extends SeqTradeStrategyStep<? super Strategy, ? super TradeStrategyStepResult, ? super TradeStrategyStepResult>> trades) {
        if (Objects.isNull(trades) || trades.isEmpty())
            throw new IllegalArgumentException("trades can't be null or empty");
        TradeStrategyStepResult result = first.run(strategy);


//        for (SeqTradeStrategyStep<? super Strategy, ? super TradeStrategyStepResult, ? super TradeStrategyStepResult> step : trades) {
//            if (Objects.isNull(result))
//                return null;
//            result = step.run(strategy, result);
//        }
        return result;
    }
}
