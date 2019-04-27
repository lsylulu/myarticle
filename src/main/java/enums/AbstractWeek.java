package enums;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 枚举类可以有抽象方法，但是必须在它的实例中实现
 * @author asus
 */
public enum AbstractWeek {

    MONDAY(0, "星期一") {
        @Override
        public AbstractWeek getNextDay() {
            return TUESDAY;
        }
    }, TUESDAY(1, "星期二") {
        @Override
        public AbstractWeek getNextDay() {
            return WEDNESDAY;
        }
    }, WEDNESDAY(2, "星期三") {
        @Override
        public AbstractWeek getNextDay() {
            return THURSDAY;
        }
    }, THURSDAY(3, "星期四") {
        @Override
        public AbstractWeek getNextDay() {
            return FRIDAY;
        }
    }, FRIDAY(4, "星期五") {
        @Override
        public AbstractWeek getNextDay() {
            return SATURDAY;
        }
    }, SATURDAY(5, "星期六") {
        @Override
        public AbstractWeek getNextDay() {
            return SUNDAY;
        }
    }, SUNDAY(6, "星期日") {
        @Override
        public AbstractWeek getNextDay() {
            return MONDAY;
        }
    };

    private int num;
    private String desc;

    AbstractWeek(int num, String desc) {
        this.num = num;
        this.desc = desc;
    }

    //一个抽象方法
    public abstract AbstractWeek getNextDay();

    public String getDesc() {
        return desc;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        String nextDay=AbstractWeek.MONDAY.getNextDay().toString();
//        System.out.println(nextDay);

//        //根据反射获取常量类
//        Class c2 = Class.forName("solution1.AbstractWeek");
//        //获取常量类中的所有内部类
//        Class innerClazz[] = c2.getDeclaredClasses();
//        //遍历内部内
//        for (Class class1 : innerClazz) {
//            //判断类是不是枚举类
//            if (class1.isEnum()) {
//                //获取内部内的类名，在这里其实就是获取枚举类
//                String simpleName = class1.getSimpleName();
//                //反射获取枚举类
//                Class<Enum> clazz = (Class<Enum>) Class.forName("solution1.AbstractWeek$" + simpleName);
//                //获取所有枚举实例
//                Enum[] enumConstants = clazz.getEnumConstants();
//                //根据方法名获取方法
//                Method getCode = clazz.getMethod("getDesc");
//                for (Enum enum1 : enumConstants) {
//                    //得到枚举实例名
//                    String name2 = enum1.name();
//                    //执行枚举方法获得枚举实例对应的值
//                    Object invoke = getCode.invoke(enum1);
//                    System.out.println(name2 + ":" + invoke.toString());
//                }
//            }
//        }
        // 1.得到枚举类对象
        Class<?> clazz = AbstractWeek.class;
        // 2.得到枚举类中的所有实例
        Object[] objects = clazz.getEnumConstants();
        Method getDesc = clazz.getMethod("getDesc");
        Method getNextDay = clazz.getMethod("getNextDay");
        for (Object obj : objects){
            // 3.调用对应方法，得到枚举常量中字段的值
            System.out.println("desc=" + getDesc.invoke(obj) + "; nextDay=" + getNextDay.invoke(obj));
        }


    }
}
