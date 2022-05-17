import com.zzg.annotations.RoleService;
import com.zzg.entity.Person;
import com.zzg.service.UserService;

import java.util.*;

/**表达式
 * Lambda
 */
public class LambdaTest {

    public static void main(String[] args) {
        // 通过匿名类，实现化线程实例化
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("使用匿名类, 完成Thread 线程实例化");
            }
        }).start();

        // 通过Lambda表达式,完成线程实例化
        new Thread(()->{
            System.out.println("使用Lambda, 完成Thread 线程实例化");
        }).start();

        // 接口实现,通过匿名类方式实现
        sayImpl(new UserService() {
            @Override
            public void say() {
                System.out.println("使用匿名类, 完成UserService 接口实例化");
            }
        });

        // 接口实现,通过Lambda 实现
        sayImpl(()->{
            System.out.println("使用Lambda, 完成UserService 接口实例化");
        });

        List<Person> list = initPerson();
        // Comparator 接口排序, 通过匿名类实现
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        // Java8 流遍历输出
        list.stream().forEach(System.out::println);

        List<Person> lambdaList = initPerson();
        // Comparator 接口排序, 通过Lambda实现
        Collections.sort(lambdaList, (Person o1, Person o2)->{
            return o1.getAge()-o2.getAge();
        });
        // Java8 流遍历输出
        lambdaList.stream().forEach(System.out::println);


        // 接口实现,通过匿名类方式实现
        showImpl(new RoleService() {
            @Override
            public void show() {
                System.out.println("使用匿名类, 完成RoleService 接口实例化");
            }
        });

        // 接口实现,通过Lambda 实现
        showImpl(()->{
            System.out.println("使用Lambda, 完成RoleService 接口实例化");
        });
    }

    public static void sayImpl(UserService service){
        service.say();
    }

    public static void showImpl(RoleService roleService){
        roleService.show();
    }

    public static List<Person> initPerson(){
        return Arrays.asList(new Person("张三",33,175), new Person("李四",43,185), new Person("王五",38,177),new Person("赵六",23,170));
    }
}
