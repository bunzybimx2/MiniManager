package com.example.omnissiah.minimanager;

import java.util.ArrayList;

/**
 * Created by User on 07/04/2016.
 */
public class Day {

    private String name;
    private ArrayList<Employee> employeeList = new ArrayList<Employee>();

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Employee> getEmployeeList(){
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

}
