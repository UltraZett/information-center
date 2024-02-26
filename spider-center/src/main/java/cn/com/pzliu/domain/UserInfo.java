package cn.com.pzliu.domain;

/**
 * @author Naive
 * @date 2024-02-23 12:11
 */
public class UserInfo {
    private String email;
    private String name;


    public UserInfo() {
    }


    public UserInfo(String email, String name) {
        this.email = email;
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
