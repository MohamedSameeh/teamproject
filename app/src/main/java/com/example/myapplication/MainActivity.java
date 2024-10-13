package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {
    Button btn_curly1,btn_curly2, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_00, btn_decimal,btn_modulus,btn_delAll,btn_delete, btn_equal, btn_minus, btn_multiply ,  btn_divide ,btn_plus;
    Button btn_sin, btn_cos, btn_tan, btn_pow;
    TextView tv_operations, tv_result;
    String number = "";
    boolean lastNumeric = false;
    boolean lastDot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link TextViews
        tv_operations = findViewById(R.id.tv_operations);
        tv_result = findViewById(R.id.tv_result);

        // Number buttons
        btn_curly1=findViewById(R.id.btn_curlyBracket1);
        btn_curly2=findViewById(R.id.btn_curlyBracket2);
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
//        btn_00 = findViewById(R.id.btn_00);
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

        // Scientific buttons
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
//        btn_pow = findViewById(R.id.btn_pow);

        // Set listeners for all buttons
        setNumberListeners();
        setOperationListeners();
    }

    private void setNumberListeners() {
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (button.getText().equals("sin") || button.getText().equals("cos") || button.getText().equals("tan")) {
                    // Add Math. to trigonometric functions for proper evaluation
                    number +=button.getText() + "(";
                } else {
                    number += button.getText();
                }
                tv_operations.setText(number);
                lastNumeric = true;
            }
        };

        // Set listeners for numbers
        btn_curly1.setOnClickListener(numberClickListener);
        btn_curly2.setOnClickListener(numberClickListener);
        btn_cos.setOnClickListener(numberClickListener);
        btn_tan.setOnClickListener(numberClickListener);
        btn_sin.setOnClickListener(numberClickListener);
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
//        btn_00.setOnClickListener(numberClickListener);

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
        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if(lastNumeric) {
                    number += button.getText();
                    tv_operations.setText(number);
                    lastNumeric = false;
                    lastDot = false;
                }

            }
        };

        // Set listeners for operations
        btn_plus.setOnClickListener(operationClickListener);
        btn_minus.setOnClickListener(operationClickListener);
        btn_multiply.setOnClickListener(operationClickListener);
        btn_divide.setOnClickListener(operationClickListener);
        btn_modulus.setOnClickListener(operationClickListener);
//        btn_pow.setOnClickListener(operationClickListener);

        // Equal button listener
        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = tv_operations.getText().toString();

                // Only evaluate if the last input was numeric
                if (lastNumeric) {
                    // Replace sin, cos, tan with Math.sin, Math.cos, Math.tan
                    expression = expression.replace("sin", "Math.sin")
                            .replace("cos", "Math.cos")
                            .replace("x", "*")
                            .replace("tan", "Math.tan");

                    // Handling power function (^): Using regex to wrap base and exponent in Math.pow(base, exponent)
                    expression = expression.replaceAll("(\\d+)\\^(\\d+)", "Math.pow($1,$2)");

                    // Check for division by zero
                    if (expression.contains("/0")) {
                        tv_result.setText("Error");
                        return;
                    }

                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);
                    String result = "";
                    try {
                        Scriptable scriptable = rhino.initStandardObjects();
                        result = rhino.evaluateString(scriptable, expression, "javascript", 1, null).toString();

                        // Additional check: If result is "Infinity", also treat it as an error
                        if (result.equals("Infinity")) {
                            result = "Error";
                        } else {
                            // Convert the result to a double
                            double doubleResult = Double.parseDouble(result);

                            // Check if it's actually an integer
                            if (doubleResult == Math.floor(doubleResult)) {
                                // If it is an integer, cast it to an int and show without decimal
                                result = String.valueOf((int) doubleResult);
                            }
                        }
                    } catch (Exception e) {
                        result = "Error";
                    } finally {
                        Context.exit();
                    }

                    tv_result.setText(result);
                }
            }
        });


        // Clear button listener
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

        // Delete (backspace) button listener
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
}
