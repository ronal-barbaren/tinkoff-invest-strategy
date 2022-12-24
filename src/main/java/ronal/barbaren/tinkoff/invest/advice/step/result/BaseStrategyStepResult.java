package ronal.barbaren.tinkoff.invest.advice.step.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseStrategyStepResult implements StrategyStepResult {
    private final String name;
}
