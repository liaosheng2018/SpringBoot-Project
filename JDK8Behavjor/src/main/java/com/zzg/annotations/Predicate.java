package com.zzg.annotations;

public interface Predicate<T> {
    boolean isValid(T t);
}
