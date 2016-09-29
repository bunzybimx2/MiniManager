package com.example.omnissiah.minimanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Wage extends AppCompatActivity implements View.OnClickListener{
    private Button Calculate_Btn;
    private TextView tx_WageCalculator;
    private EditText input_total,input_wageRate,input_hours,input_overTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wage__calculator);
        init();
    }

    private void init(){
        Calculate_Btn = (Button)findViewById(R.id.Calculate_Btn);
        input_total = (EditText)findViewById(R.id.input_total);
        input_wageRate = (EditText)findViewById(R.id.input_wageRate);
        input_hours = (EditText)findViewById(R.id.input_hours);
        input_overTime = (EditText)findViewById(R.id.input_overTime);

        Calculate_Btn.setOnClickListener(this);
    }

    public void Send(View view){
        String Total1 = input_total.getText().toString();
        Intent i = new Intent(getApplicationContext(),Stock.class);
        i.putExtra("total",Total1);
        startActivity(i);

    }

    @Override
    public void onClick(View v){

        String Total2 = input_wageRate.getText().toString();
        String Total3 = input_hours.getText().toString();
        String Total4 = input_overTime.getText().toString();
            switch (v.getId()){
                case R.id.Calculate_Btn:
                double wage = Integer.parseInt(Total2) * Integer.parseInt(Total3);
                double over = (Integer.parseInt(Total2) * Integer.parseInt(Total4)) * 1.5;
                double total = (wage + over)*52;
                double net =(total-(total*.2))/52;
                    input_total.setText(String.valueOf(net));


                    break;
            }
    }
}