package com.example.wrapper;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class emplyoee {

    // {"id":1,"employee_name":"Tiger
    // Nixon","employee_salary":320800,"employee_age":61,"profile_image":""}
    @Id
    private String id;
    private String employee_name;
    private String employee_salary;
    private int employee_age;
    private String profile_image;

    public emplyoee() {

    }

    public emplyoee(String id, String emplyoee_name, String employee_salary, int employee_age, String profile_image){
        this.id = id;
        this.employee_name = emplyoee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(String employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    
    
}
