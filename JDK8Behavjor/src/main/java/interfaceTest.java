import com.zzg.annotations.InterfaceExpand;

/**
 * 接口新增方法
 */
public class interfaceTest {
    public static void main(String[] args) {
        InterAddMethodImpl((item)->{
            System.out.println("抽象方法调用:" + item);
        });
    }

    public static void InterAddMethodImpl(InterfaceExpand expand){
        String defaultVale =expand.defaultMethod();
        System.out.println("默认方法调用:" + defaultVale);

        String staticValue = InterfaceExpand.staticMethod();
        System.out.println("静态方法调用:" + staticValue);

        expand.abstrMethod("Hi, 抽象方法");
    }

}
