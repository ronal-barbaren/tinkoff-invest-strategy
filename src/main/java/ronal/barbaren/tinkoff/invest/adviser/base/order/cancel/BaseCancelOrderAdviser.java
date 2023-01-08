package ronal.barbaren.tinkoff.invest.adviser.base.order.cancel;

import com.google.common.base.Strings;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel.BaseCancelOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel.CancelOrderAdvice;

public abstract class BaseCancelOrderAdviser implements CancelOrderAdviser {
    @Getter
    private final String name;
    private final CancelOrderAdvice skip;

    protected BaseCancelOrderAdviser(String name) {
        this.name = name;
        this.skip = new BaseCancelOrderAdvice(name, null);
    }

    protected CancelOrderAdvice order(String orderId) {
        if (Strings.isNullOrEmpty(orderId))
            throw new IllegalArgumentException("order id can't be null or empty");
        return new BaseCancelOrderAdvice(name, orderId);
    }

    protected CancelOrderAdvice skip() {
        return skip;
    }
}
