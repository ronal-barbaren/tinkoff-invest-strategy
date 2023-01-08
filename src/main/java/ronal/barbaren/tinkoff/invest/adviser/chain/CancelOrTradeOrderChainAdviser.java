package ronal.barbaren.tinkoff.invest.adviser.chain;

import com.google.common.base.Strings;
import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.Advice;
import ronal.barbaren.tinkoff.invest.adviser.advice.order.cancel.CancelOrderAdvice;
import ronal.barbaren.tinkoff.invest.adviser.base.CompositeAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.order.cancel.CancelOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.base.order.trade.TradeOrderAdviser;
import ronal.barbaren.tinkoff.invest.adviser.utils.AdviserUtils;

import javax.annotation.Nonnull;

@Getter
public class CancelOrTradeOrderChainAdviser implements CompositeAdviser {
    private final String name;
    private final CancelOrderAdviser cancel;
    private final TradeOrderAdviser trade;

    public CancelOrTradeOrderChainAdviser(CancelOrderAdviser cancel, TradeOrderAdviser trade) {
        this.name = AdviserUtils.getCompositeName(cancel, trade);
        this.cancel = cancel;
        this.trade = trade;
    }

    @Nonnull
    @Override
    public Advice getAdvice() {
        CancelOrderAdvice order = cancel.getCloseOrder();
        if (!Strings.isNullOrEmpty(order.getOrderId()))
            return order;
        return trade.getTrade();
    }
}
