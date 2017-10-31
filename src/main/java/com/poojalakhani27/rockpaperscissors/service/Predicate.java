package com.poojalakhani27.rockpaperscissors.service;

import org.springframework.stereotype.Component;

@Component
public interface Predicate<T> {
    boolean apply(T t);
}
