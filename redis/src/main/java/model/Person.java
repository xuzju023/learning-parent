package model;

import java.io.Serializable;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:53 2019/5/5
 */
public class Person implements Serializable {
    private String userName;
    private String passWord;

    public Person(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
