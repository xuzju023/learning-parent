package com.xzj.model;


import javax.security.auth.Subject;
import java.io.IOException;

/**
 * @date 2019/9/29 17:22
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Student implements Man,Cloneable{
    public  String name;
    public  Integer age;
    public Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student( ) {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) throws SelfDefException {
        this.age = age;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //深拷贝
        try {
            // 直接调用父类的clone()方法
            Student student = (Student) super.clone();
            student.setPerson((Person) person.clone());
            return student;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", person=" + person +
                '}';
    }
}
