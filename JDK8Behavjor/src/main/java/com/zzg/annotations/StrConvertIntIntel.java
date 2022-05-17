package com.zzg.annotations;

@FunctionalInterface
public interface StrConvertIntIntel<T, R> {
    /**
     * 实现字符串转int
     * @param t
     * @return
     */
    R apply(T t);
}
