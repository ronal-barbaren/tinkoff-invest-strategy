package ronal.barbaren.tinkoff.invest.adviser.step.utils;

import lombok.experimental.UtilityClass;
import ronal.barbaren.tinkoff.invest.adviser.Adviser;
import ronal.barbaren.tinkoff.invest.adviser.step.SeqTradeAdviceStep;
import ronal.barbaren.tinkoff.invest.adviser.step.AdviceStep;
import ronal.barbaren.tinkoff.invest.adviser.step.TradeAdviceStep;
import ronal.barbaren.tinkoff.invest.adviser.step.result.AdviceStepResult;
import ronal.barbaren.tinkoff.invest.adviser.step.result.TradeAdviceStepResult;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class AdviceStepUtils {
    @Nullable
    public static <T extends Adviser> AdviceStepResult anyFilter(T strategy, List<AdviceStep<T, ? extends AdviceStepResult>> filters) {
        if (Objects.isNull(filters) || filters.isEmpty())
            throw new IllegalArgumentException("filters can't be null or empty");
        for (AdviceStep<T, ? extends AdviceStepResult> filter : filters) {
            AdviceStepResult result = filter.run(strategy);
            if (Objects.nonNull(result))
                return result;
        }
        return null;
    }

    @Nullable
    public static <T extends Adviser, E extends TradeAdviceStep<T, ? extends TradeAdviceStepResult>> TradeAdviceStepResult anyTrade(T strategy, List<E> trades) {
        if (Objects.isNull(trades) || trades.isEmpty())
            throw new IllegalArgumentException("trades can't be null or empty");
        for (E filter : trades) {
            TradeAdviceStepResult result = filter.run(strategy);
            if (Objects.nonNull(result))
                return result;
        }
        return null;
    }

    @Nullable
    public static TradeAdviceStepResult seqTrade(
            Adviser adviser,
            TradeAdviceStep<? super Adviser, ? super TradeAdviceStepResult> first,
            List<? extends SeqTradeAdviceStep<? super Adviser, ? super TradeAdviceStepResult, ? super TradeAdviceStepResult>> trades) {
        if (Objects.isNull(trades) || trades.isEmpty())
            throw new IllegalArgumentException("trades can't be null or empty");
        TradeAdviceStepResult result = first.run(adviser);


//        for (SeqTradeStrategyStep<? super Strategy, ? super TradeStrategyStepResult, ? super TradeStrategyStepResult> step : trades) {
//            if (Objects.isNull(result))
//                return null;
//            result = step.run(strategy, result);
//        }
        return result;
    }
}
