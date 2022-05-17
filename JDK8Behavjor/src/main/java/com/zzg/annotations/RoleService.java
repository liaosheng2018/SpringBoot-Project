package com.zzg.annotations;

/**
 *  @FunctionalInterface:被该注解修饰的接口只能声明一个抽象方法
 *  JDK8接口中的静态方法和默认方法，都不算是抽象方法。
 *  接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。
 */
@FunctionalInterface
public interface RoleService {
    void show();
}
