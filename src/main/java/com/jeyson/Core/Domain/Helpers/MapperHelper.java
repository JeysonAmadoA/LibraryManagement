package com.jeyson.Core.Domain.Helpers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class MapperHelper {

    public static void updateFieldIfNotNull(Long value, Consumer<Long> updateFunction) {
        if (value != null) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNotNull(String value, Consumer<String> updateFunction) {
        if (value != null) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNotNull(LocalDate value, Consumer<LocalDate> updateFunction) {
        if (value != null) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNumberNotZero(short value, Consumer<Short> updateFunction) {
        if (value != 0) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNumberNotZero(int value, IntConsumer updateFunction) {
        if (value != 0) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNumberNotZero(float value, Consumer<Float> updateFunction) {
        if (value != 0) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNumberNotZero(BigDecimal value, Consumer<BigDecimal> updateFunction) {
        if (value != null) {
            updateFunction.accept(value);
        }
    }
}
