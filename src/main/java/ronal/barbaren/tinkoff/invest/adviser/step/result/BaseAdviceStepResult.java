package ronal.barbaren.tinkoff.invest.adviser.step.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseAdviceStepResult implements AdviceStepResult {
    private final String name;
}
