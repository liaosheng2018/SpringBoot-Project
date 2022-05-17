import com.zzg.annotations.InterfaceExpand;
import com.zzg.annotations.Predicate;
import com.zzg.annotations.StrConvertIntIntel;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FuncIntefaceTest {
    public static void main(String[] args) {
        int maxValue = getMax(() ->{
            int arr[] = {23,56,78,67,103};
            // 计算出数组中的最大值
            Arrays.sort(arr);
            return arr[arr.length-1];
        });
        System.out.println("最大值:" + maxValue);


        lowerCase("java8", (item)->{
            System.out.println("字符大写:" + item.toUpperCase());
        });

        Integer num = strConvertInt("100", (item)->{
            return Integer.parseInt(item);
        });
        System.out.println("str转换int:" + num);

        Boolean flag = isValid("Java8特效",(item)->{
            if(item.length() >= 3){
                return true;
            }
            return false;
        });
        System.out.println("字符串长度是否符合规定:" + flag);
    }

    /**
     * Supplier:无参有返回值的接口
     * @param supplier
     * @return
     */
    public static int getMax(Supplier<Integer> supplier){
        Integer max = supplier.get();
        return max;
    }

    /**
     * Consumer:有参无返回值得接口
     * @param str
     * @param consumer
     */
    public static void lowerCase(String str, Consumer<String> consumer){
        consumer.accept(str);
    }

    /**
     * Function: 有参有返回值的接口，Function接口是根据一个类型的数据得到另一个类型的数据，前者称为前置条
     * 件，后者称为后置条件。有参数有返回值。
     * @param str
     * @param function
     * @return
     */
    public static int strConvertInt(String str, StrConvertIntIntel<String, Integer> function){
        return function.apply(str);
    }

    /**
     * Predicate:有参且返回值为Boolean的接口
     * @param str
     * @param function
     * @return
     */
    public static boolean isValid(String str, Predicate<String> function){
        return function.isValid(str);
    }


}
