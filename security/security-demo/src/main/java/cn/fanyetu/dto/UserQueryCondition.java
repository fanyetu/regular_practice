package cn.fanyetu.dto;

/**
 * @author zhanghaonan
 * @date 2018/1/14
 */
public class UserQueryCondition {

    private String username;

    private int age;

    private int ageTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }
}
