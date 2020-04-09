package hashcode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Student {

    private int no;

    private String name;

    public static void main(String[] args) {
        Student student1=new Student();
        student1.setName("张三");
        student1.setNo(12);
        System.out.println(student1.hashCode());
        Map<Student,String> map=new HashMap<>();
        map.put(student1,"student1");
        student1.setName("111");
        System.out.println(student1.hashCode());
        System.out.println(map.get(student1));
    }
}
