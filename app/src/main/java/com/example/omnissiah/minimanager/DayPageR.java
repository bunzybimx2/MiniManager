package com.example.omnissiah.minimanager;

import java.util.ArrayList;

/**
 * Created by User on 17/04/2016.
 */
public class DayPageR {

    private String name;
    private ArrayList<EmployeeListR> employeeListRR = new ArrayList<EmployeeListR>();

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<EmployeeListR> getEmployeeListRR(){
        return employeeListRR;
    }

    public void setEmployeeListRR(ArrayList<EmployeeListR> employeeListRR){
        this.employeeListRR = employeeListRR;
    }

}
