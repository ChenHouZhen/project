package com.chenhz.excel.Entity;

import com.chenhz.excel.utils.DateUtils;
import com.chenhz.excel.utils.ExcelField;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {

    private String userName;

    private String nickName;

    private Integer age;

    private Date birth;

    @NotNull(message = "User Name 不能为空")
    @ExcelField(title="用户名", align=2, sort=1)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title="网名", align=2, sort=2)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ExcelField(title="年龄", align=2, sort=3)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonFormat(pattern = "mm/dd/YYYY")
    @NotNull(message="Birth Day不能为空")
    @ExcelField(title="出生日期", align=2, sort=4)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", birth=" + DateUtils.formatDate(birth,"yyyy-MM-dd") +
                '}';
    }
}
