package edu.zju.cst.AnomalyDetection.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {

    private final Supplier<T> instantiator;
    private List<Consumer<T>> instantiatorModifiers = new ArrayList<>();

    public GenericBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
        return new GenericBuilder<T>(instantiator);
    }

    public <U> GenericBuilder<T> with(BiConsumer<T, U> consumer, U value) {
        Consumer<T> c = instance -> consumer.accept(instance, value);
        instantiatorModifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        instantiatorModifiers.forEach(modifier -> modifier.accept(value));
        instantiatorModifiers.clear();
        return value;
    }
}


