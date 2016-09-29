package com.example.omnissiah.minimanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Income extends AppCompatActivity implements View.OnClickListener {
    private Button Calculate_Btn;
    private TextView tx_IncomeCalculator,tv_result;
    private EditText input_stock, input_wage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_calculator);
        init();

        Intent i = getIntent();
        String STotal = i.getStringExtra("Stock_Total");
        String WTotal = i.getStringExtra("Wage_Total");
        input_stock.setText(STotal);
        input_wage.setText(WTotal);
    }

    private void init(){
        Calculate_Btn = (Button)findViewById(R.id.Calculate_Btn);
        tx_IncomeCalculator = (TextView) findViewById(R.id.tx_IncomeCalculator);
        input_stock = (EditText)findViewById(R.id.input_stock);
        input_wage = (EditText)findViewById(R.id.input_wage);
        tv_result = (TextView) findViewById(R.id.tv_result);

        Calculate_Btn.setOnClickListener(this);
    }

    public JSONArray createJSON(){
        JSONArray Arr = new JSONArray();
        JSONObject Obj = new JSONObject();
        try{

            Obj.put("total", tv_result.getText().toString());
            Arr.put(Obj);

            FileWriter file = new FileWriter("file", true);
            file.write(Obj.toString());
            file.flush();
            file.close();



        }
        catch (JSONException e) {
            System.out.println("Error:" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arr;
    }


/*
    private Void main() throws IOException{
        File fileMetadata = file;
        fileMetadata.setName("config.json");
        fileMetadata.setParents(Collections.singletonList("appDataFolder"));
        java.io.File filePath = new java.io.File("files/config.json");
        FileContent mediaContent = new FileContent("application/json", filePath);
        File file = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());

    }


private void run(){
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try{
                main();
            } catch (UserRecoverableAuthException e) {
                e.printStackTrace();
                startActivityForResult(e,getIntent(),REQUEST_AUTHOTISATION);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    });

}
*/

    @Override
    public void onClick(View v) {
        String total1 = input_stock.getText().toString();
        String total2 = input_wage.getText().toString();
        switch(v.getId()){
            case R.id.Calculate_Btn:
                double gross = Double.parseDouble(total1) - Double.parseDouble(total2);
                double tax = gross * 0.125;
                double total = gross - tax;
                tv_result.setText(String.valueOf(total));
                createJSON();
                break;
        }
    }
}
