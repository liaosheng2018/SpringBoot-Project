import com.zzg.entity.Person;

import java.util.Optional;

/**
 * Optional:Optional是一个没有子类的工具类，Optional是一个可以为null的容器对象，它的主要作用就是为了避
 * 免Null检查，防止NullpointerException
 */
public class OptionalTest {
    public static void main(String[] args) {
        optionalConstructor();
        optionalCommonMethod();
    }

    /**
     * Optional 创建方式总结
     */
    public static void optionalConstructor() {
        // 方式一：基于Optional.of, 温馨提示：对象不能为null,否则提示异常
        Optional<String> constr = Optional.of("OptionalStr");

        // 方式二: 基于Optional.ofNullable, 温馨 提示 ：对象可以为空
        Optional<Object> conStrNull = Optional.ofNullable(null);
        Optional<Integer> conStrInteger = Optional.ofNullable(100);

    }

    /**
     * Optional中的常用方法介绍
     * get(): 如果Optional有值则返回，否则抛出NoSuchElementException异常
     * 温馨提示：get()通常和isPresent方法一块使用
     * isPresent():判断是否包含值，包含值返回true，不包含值返回false
     * orElse(T t):如果调用对象包含值，就返回该值，否则返回t
     * orElseGet(Supplier s):如果调用对象包含值，就返回该值，否则返回 Lambda表达式的返 回值
     */

    public static void optionalCommonMethod() {
        // Optional 构造
        Optional<String> operationalStr = Optional.ofNullable("zhouzhiwengang");
        Optional<String> operationalNull = Optional.empty();

        // 判断Optional是否为空，再获取Optional 值
        if (operationalStr.isPresent()) {
            System.out.println("operationalStr 值为:" + operationalStr.get());
        } else {
            System.out.println("operationalStr 值为空");
        }

        if (operationalNull.isPresent()) {
            System.out.println("operationalStr 值为:" + operationalNull.get());
        } else {
            System.out.println("operationalStr 值为空");
        }

        // 判断Optional 是否存在值，不存在读取orElse 设置的值
        String value = operationalNull.orElse("defaultVal");
        System.out.println("operationalStr 默认值:" + value);

        // 判断Optional 是否存在值, 不存在读取orElseGet 设置的值
        String UpperValue = operationalNull.orElseGet(() ->{
            // 字母转大写
            return "default_Value".toUpperCase();
        });
        System.out.println("operationalStr 默认值:" + UpperValue);

        // 判断Optional值是否存在(ifPresent), 存在则执行lambad 表达式,否则不执行
        operationalStr.ifPresent(item ->{
            System.out.println("Optional值存在,值为:" + item);
        });

        operationalStr.ifPresent(System.out::println);

        // Optional 高级用法
        Optional<Person> template = Optional.ofNullable(new Person("zcx", 3, 110));
        String nameUpper = getNameToUpper(template);
        System.out.println("Person 之name属性转换为大写:" + nameUpper);
    }

    public static String getNameToUpper(Optional<Person> template) {
        if(template.isPresent()){
            return template.map(Person::getName).map(String::toUpperCase).orElse("");
        }
        return "";
    }


}
