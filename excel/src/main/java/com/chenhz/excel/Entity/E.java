package com.chenhz.excel.Entity;

import com.chenhz.excel.utils.ExcelField;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

public class E {

    public String name;

    public String email;

    public Date DateOfBirth;

    public Double salary;

    @ExcelField(title = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "Email")
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title = "Date Of Birth")
    @JsonFormat(pattern = "mm/dd/YYYY")
    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
    @ExcelField(title = "Salary")
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "E{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", salary=" + salary +
                '}';
    }
}
