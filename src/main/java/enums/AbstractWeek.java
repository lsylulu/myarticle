package enums;

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

    public static void main(String[] args) throws Exception{
        String nextDay=AbstractWeek.MONDAY.getNextDay().toString();
        System.out.println(nextDay);

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
