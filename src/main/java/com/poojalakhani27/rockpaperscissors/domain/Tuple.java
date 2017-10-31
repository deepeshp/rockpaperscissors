package com.poojalakhani27.rockpaperscissors.domain;

/**
 * Represents a Tuple where the order of entries does not matter.
 * e.g.
 * Tuple<Animal> catAndDog is EQUALTO Tuple<Animal> dogAndCat
 * @param <T> The type of tuple entries
 *
 * @author Pooja Lakhani
 */
public class Tuple<T> {
    private final T value1;
    private final T value2;

    public Tuple(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public T getValue2() {
        return value2;
    }

    @Override
    public int hashCode() {
        return value1.hashCode() + value2.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?> tuple = (Tuple<?>) o;

        if ((value1.equals(tuple.value1) && value2.equals(tuple.value2)) || (value2.equals(tuple.value1) && value1.equals(tuple.value2)))
            return true;
        return false;

    }
}
