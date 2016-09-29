package com.example.omnissiah.minimanager;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {


    private Button EmployeeList_Btn,Income_Btn,Stock_Btn,Wage_Btn,Calendar_Btn;



    public void Income(View view){
        Intent startNewActivity = new Intent(this,Income.class);
        startActivity(startNewActivity);
    }




    public void Wage(View view){
        Intent startNewActivity = new Intent(this,Wage.class);
        startActivity(startNewActivity);
    }

    public void CalendarActivity(View view){
        Intent startNewActivity = new Intent(this,CalendarActivity.class);
        startActivity(startNewActivity);
    }

    public void Stock(View view){
        Intent startNewActivity = new Intent(this,Stock.class);
        startActivity(startNewActivity);
    }

    public void employee(View view){
        Intent startNewActivity = new Intent(this,Availability.class);
        startActivity(startNewActivity);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

}
