package enums;

/**
 * @author asus
 */

public enum Week {
    MONDAY(0, "星期一"),
    TUESDAY(1, "星期二"),
    WEDNESDAY(2, "星期三"),
    THURSDAY(3, "星期四"),
    FRIDAY(4, "星期五"),
    SATURDAY(5, "星期六"),
    //必须要用分号结束
    SUNDAY(6, "星期日");

    private int num;
    private String desc;

    /**
     * 构造方法必然是private修饰的
     * 就算不写，也是默认的
     *
     * @param num
     * @param desc
     */
    private Week(int num, String desc) {
        this.num=num;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getNum() {
        return num;
    }

    /**
     * 用switch重写toString方法，提高代码健壮性
     * @return
     */
    @Override
    public String toString() {

        switch (this) {

            case MONDAY:
                return "今天星期一";
            case TUESDAY:
                return "今天星期二";
            case WEDNESDAY:
                return "今天星期三";
            case THURSDAY:
                return "今天星期四";
            case FRIDAY:
                return "今天星期五";
            case SATURDAY:
                return "今天星期六";
            case SUNDAY:
                return "今天星期日";
            default:
                return "Unknow Day";
        }

    }

    public static void main(String[] args) {
//        //遍历Week枚举类
//        for (Week day : Week.values()) {
//            System.out.println("name:" + day.name() +
//                    ",desc:" + day.getDesc());
//        }
//        //通过values()获取枚举数组
//        Week[] weeks = Week.values();
//        //java.lang.IllegalArgumentException
//        System.out.println(Week.valueOf("MONDAY"));
//        //返回对应的name属性
//        System.out.println(Week.FRIDAY.toString());
//        //返回4，根据我们定义的次序，从0开始。如果在定义时调换FRIDAY
//        //的次序，返回的数字也会对应的变化
//        System.out.println(Week.FRIDAY.ordinal());




    }

}
