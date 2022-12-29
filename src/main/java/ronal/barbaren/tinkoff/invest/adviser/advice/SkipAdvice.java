package ronal.barbaren.tinkoff.invest.adviser.advice;

import lombok.Getter;
import ronal.barbaren.tinkoff.invest.adviser.advice.result.AdviceResult;
import ronal.barbaren.tinkoff.invest.adviser.step.result.AdviceStepResult;

import javax.annotation.Nullable;

@Getter
public class SkipAdvice implements Advice<AdviceResult> {
    private final String name;

    public SkipAdvice(AdviceStepResult result) {
        this.name = result.getName();
    }

    public SkipAdvice(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public AdviceResult getResult() {
        return null;
    }
}
