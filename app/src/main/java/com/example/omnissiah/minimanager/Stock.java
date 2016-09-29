package com.example.omnissiah.minimanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Stock extends AppCompatActivity implements View.OnClickListener{
    private Button Calculate_Btn;
    private TextView tx_StockCalculator,tv_result, textView2;
    private EditText input_stockValue, input_taxRate01, input_taxRate02, input_taxRate03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        init();


        Intent i = getIntent();
        String total = i.getStringExtra("total");
        textView2.setText(total);
    }

    private void init() {
        Calculate_Btn = (Button) findViewById(R.id.Calculate_Btn);
        tx_StockCalculator = (TextView) findViewById(R.id.tx_StockCalculator);
        input_stockValue = (EditText) findViewById(R.id.input_stockValue);
        input_taxRate01 = (EditText) findViewById(R.id.input_taxRate01);
        input_taxRate02 = (EditText) findViewById(R.id.input_taxRate02);
        input_taxRate03 = (EditText) findViewById(R.id.input_taxRate03);
        tv_result = (TextView) findViewById(R.id.tv_result);
        textView2 = (TextView) findViewById(R.id.textView2);

        Calculate_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String num1 = input_stockValue.getText().toString();
        String num2 = input_taxRate01.getText().toString();
        String num3 = input_taxRate02.getText().toString();
        String num4 = input_taxRate03.getText().toString();
        switch(v.getId()){
            case R.id.Calculate_Btn:
                double result = Integer.parseInt(num1) * 0.235+ Integer.parseInt(num1) * 0.135+ Integer.parseInt(num1) *0.09;
                tv_result.setText(String.valueOf(result));

                String Total2 = tv_result.getText().toString();
                String Total1 = textView2.getText().toString();
                Intent i = new Intent(getApplicationContext(),Income.class);
                i.putExtra("Stock_Total",Total2);
                i.putExtra("Wage_Total",Total1);
                startActivity(i);

                break;
        }
    }

}

