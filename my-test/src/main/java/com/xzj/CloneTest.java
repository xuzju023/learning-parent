package com.xzj;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 13:50 2019/6/5
 */
public class CloneTest {
    static class Person implements Cloneable{
        public String name;
        public int age;
        public Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address=" + address +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        Person person1 = new Person("xzj", 1);
        Address address1 = new Address();
        Address address2 = new Address();
        address1.setAddress("china");
        address2.setAddress("America");
        person1.setAddress(address1);
        Person person2=(Person) person1.clone();
        person2.setName("xx");
        person2.setAge(12);
        address1.setAddress("ss");
        System.out.println(person1);
        System.out.println("\n"+person2);
    }
}
