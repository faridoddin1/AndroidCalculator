package com.example.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private String currentNumber = "";
    private String leftOperand = "";
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        int[] numberButtonIds = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_dot};
        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        int[] operatorButtonIds = {R.id.button_add, R.id.button_subtract, R.id.button_multiply, R.id.button_divide};
        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(this::onOperatorClick);
        }

        findViewById(R.id.button_clear).setOnClickListener(this::onClearClick);
        findViewById(R.id.button_equals).setOnClickListener(this::onEqualsClick);
        findViewById(R.id.button_backspace).setOnClickListener(this::onBackspaceClick);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        currentNumber += button.getText().toString();
        updateResultTextView(currentNumber);
    }

    public void onOperatorClick(View view) {
        if (!currentNumber.isEmpty()) {
            leftOperand = currentNumber;
            currentNumber = "";
            operator = ((Button) view).getText().toString();
        }
    }

    public void onEqualsClick(View view) {
        if (!leftOperand.isEmpty() && !currentNumber.isEmpty()) {
            double result = calculate();
            updateResultTextView(String.valueOf(result));
            leftOperand = String.valueOf(result);
            currentNumber = "";
            operator = "";
        }
    }

    public void onClearClick(View view) {
        currentNumber = "";
        leftOperand = "";
        operator = "";
        updateResultTextView("0");
    }

    public void onBackspaceClick(View view) {
        if (!currentNumber.isEmpty()) {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
            updateResultTextView(currentNumber);
        }
    }

    private double calculate() {
        double num1 = Double.parseDouble(leftOperand);
        double num2 = Double.parseDouble(currentNumber);
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN; // Handle division by zero
                }
            default:
                return 0.0;
        }
    }

    private void updateResultTextView(String text) {
        textViewResult.setText(text);
    }
}
