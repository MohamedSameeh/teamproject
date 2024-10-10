package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_plus, btn_equal, btn_minus, btn_multiply, btn_00, btn_decimal, btn_delAll, btn_delete, btn_divide, btn_modulus;
    TextView tv_operations, tv_result;
    String number = "";
    boolean lastNumeric = false; // To track the last entry (number or operator)
    boolean lastDot = false; // To ensure only one decimal point per number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_operations = findViewById(R.id.tv_operations);
        tv_result = findViewById(R.id.tv_result);

        // Number buttons
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn_00 = findViewById(R.id.btn_00);
        btn_decimal = findViewById(R.id.btn_decimal);

        // Operation buttons
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_modulus = findViewById(R.id.btn_modulus);
        btn_equal = findViewById(R.id.btn_equal);
        btn_delAll = findViewById(R.id.btn_delAll);
        btn_delete = findViewById(R.id.btn_delete);

        // Set listeners for all buttons
        setNumberListeners();
        setOperationListeners();
    }

    private void setNumberListeners() {
        // Number buttons listener
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                number += button.getText();
                tv_operations.setText(number);
                lastNumeric = true;
            }
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);
        btn_00.setOnClickListener(numberClickListener);

        // Decimal button listener
        btn_decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !lastDot) {
                    number += ".";
                    tv_operations.setText(number);
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
    }

    private void setOperationListeners() {
        // Operation buttons listener
        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (lastNumeric && !isOperatorAdded(number)) {
                    number += button.getText();
                    tv_operations.setText(number);
                    lastNumeric = false;
                    lastDot = false; // Reset dot flag after operation
                }
            }
        };

        btn_plus.setOnClickListener(operationClickListener);
        btn_minus.setOnClickListener(operationClickListener);
        btn_multiply.setOnClickListener(operationClickListener);
        btn_divide.setOnClickListener(operationClickListener);
        btn_modulus.setOnClickListener(operationClickListener);

        // Equal button listener
        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric) {
                    String result = calculateResult();
                    tv_result.setText(result);
                    number = result; // Set the result for further calculations
                    lastNumeric = false; // Reset lastNumeric after calculation
                }
            }
        });

        // Clear All button listener
        btn_delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = "";
                tv_operations.setText("");
                tv_result.setText("");
                lastNumeric = false;
                lastDot = false;
            }
        });

        // Delete button listener (delete the last character)
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number.length() > 0) {
                    number = number.substring(0, number.length() - 1);
                    tv_operations.setText(number);
                }
            }
        });
    }

    private String calculateResult() {
        try {
            Context rhino = Context.enter();
            rhino.setOptimizationLevel(-1);
            Scriptable scriptable = rhino.initStandardObjects();
            String finalResult = rhino.evaluateString(scriptable, number, "JavaScript", 1, null).toString();
            return finalResult;
        } catch (Exception e) {
            return "Error";
        } finally {
            Context.exit();
        }
    }

    private boolean isOperatorAdded(String value) {
        // Check if the last character is an operator
        return value.endsWith("+") || value.endsWith("-") || value.endsWith("*") || value.endsWith("/") || value.endsWith("%");
    }
}
