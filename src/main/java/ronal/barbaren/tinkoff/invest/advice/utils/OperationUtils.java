package ronal.barbaren.tinkoff.invest.advice.utils;

import lombok.experimental.UtilityClass;
import ronal.barbaren.tinkoff.invest.wrapper.dto.operation.Operation;

import java.util.Comparator;
import java.util.Set;

@UtilityClass
public class OperationUtils {

    public static Operation getLast(Set<Operation> operations) {
        return operations.stream()
                .max(Comparator.comparing(Operation::getDate))
                .orElse(null);
    }

    public static Operation getLastBuy(Set<Operation> operations) {
        return operations.stream()
                .filter(Operation::isBuy)
                .max(Comparator.comparing(Operation::getDate))
                .orElse(null);
    }

    public static Operation getLastSell(Set<Operation> operations) {
        return operations.stream()
                .filter(Operation::isSell)
                .max(Comparator.comparing(Operation::getDate))
                .orElse(null);
    }
}
