package com.zzg.annotations;

public interface InterfaceExpand {

    /**
     * 抽象方法定义
     */
    public void abstrMethod(String value);

    /**
     * 默认方法
     * @return
     */
    public default String defaultMethod() {
        return "默认方法";
    }

    /**
     * 静态方法
     * @return
     */
    public static String staticMethod() {
        return  "静态方法";
    }
}
