package com.example.wang.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import is3102.HOTP;

public class MainActivity extends AppCompatActivity {
    private int result;
    private String currentOperator;
    private TextView calculatorDisplay;
    private int currentNumber;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = 0;
        currentOperator = "";
        calculatorDisplay = (TextView) findViewById(R.id.calculatorDisplay);
        currentNumber = 0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present. getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void digitsOnClick(View view) {
        int numberPressed = Integer.parseInt(((Button) view).getText().toString());
        currentNumber = currentNumber * 10 + numberPressed;
        if (currentOperator.equals("=")) result = currentNumber;
        calculatorDisplay.setText(String.valueOf(currentNumber));
    }

    public void operatorOnClick(View view) {
        //currentNumber
        currentNumber = Integer.parseInt(calculatorDisplay.getText().toString());
        String token = HOTP.generateSingleToken("SEED",currentNumber);
        calculatorDisplay.setText(token);
        currentOperator = ((Button) view).getText().toString();
    }

    public void allClear(View view) {
        currentNumber = 0;
        currentOperator = "";
        result = 0;
        calculatorDisplay.setText(String.valueOf(result));
    }


}
