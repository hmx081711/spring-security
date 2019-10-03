package com.dorothy.domain;

import com.dorothy.Validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

public class User {

    public interface SimpleUserView {};

    public interface UserDetailView extends SimpleUserView {};


    private Long id;

    @MyConstraint(message = "这是一个测试用例")
    private String username;

    private int age;

    @NotBlank(message = "密码必须不能为空")
    private String password;

    @Past(message = "生日必须是一个过去的时间")
    private Date date;

    @JsonView(SimpleUserView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
