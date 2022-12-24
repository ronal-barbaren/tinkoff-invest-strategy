package ronal.barbaren.tinkoff.invest.strategy.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ronal.barbaren.tinkoff.invest.wrapper.dto.amount.Amount;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Set;

@UtilityClass
public class AmountUtils {
    private static final String RUB = "RUB";
    private static final String USD = "USD";

    @Nullable
    public static BigDecimal getRubAmount(Set<Amount> amounts) {
        return getAmount(RUB, amounts);
    }

    @Nullable
    public static BigDecimal getUsdAmount(Set<Amount> amounts) {
        return getAmount(USD, amounts);
    }

    @Nullable
    public static BigDecimal getAmount(String currency, Set<Amount> amounts) {
        if (CollectionUtils.isEmpty(amounts))
            return null;
        return amounts.stream().filter(a -> currency.equals(a.getCurrency())).map(Amount::getValue).findAny().orElse(null);
    }
}
