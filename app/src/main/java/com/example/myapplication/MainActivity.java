package com.example.myapplication;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "calc_history_prefs";
    private static final String KEY_HISTORY = "history";
    private static final int REQUEST_CODE_HISTORY = 1;
    Button btn_curly1, btn_curly2, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_decimal;
    Button btn_modulus, btn_delAll, btn_delete, btn_equal, btn_minus, btn_multiply, btn_divide, btn_plus;
    Button btn_sin, btn_cos, btn_tan, btn_log, btn_sqrt, btn_pow, btn_history;
    TextView tv_operations, tv_result;
    String number = "";
    boolean lastNumeric = false;
    boolean lastDot = false;
    boolean lastOperator = false;

    ArrayList<String> history = new ArrayList<>(); // History of operations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_operations = findViewById(R.id.tv_operations);
        tv_result = findViewById(R.id.tv_result);

        // Button initialization
        btn_curly1 = findViewById(R.id.btn_curlyBracket1);
        btn_curly2 = findViewById(R.id.btn_curlyBracket2);
        btn_cos = findViewById(R.id.btn_cos);
        btn_sin = findViewById(R.id.btn_sin);
        btn_tan = findViewById(R.id.btn_tan);
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
        btn_decimal = findViewById(R.id.btn_decimal);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_modulus = findViewById(R.id.btn_modulus);
        btn_equal = findViewById(R.id.btn_equal);
        btn_delAll = findViewById(R.id.btn_delAll);
        btn_delete = findViewById(R.id.btn_delete);
        btn_log = findViewById(R.id.btn_log);
        btn_sqrt = findViewById(R.id.btn_sqrt);
        btn_pow = findViewById(R.id.btn_pow);
        btn_history = findViewById(R.id.btn_history);

        // Set listeners for buttons
        setNumberListeners();
        setOperationListeners();
    }

    private void setNumberListeners() {
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                number += button.getText();
                tv_operations.setText(number);
                lastNumeric = true;
                lastOperator = false;
                lastDot = false;  // Reset lastDot on number input
            }
        };

        // Number button click listeners
        btn_curly1.setOnClickListener(numberClickListener);
        btn_curly2.setOnClickListener(numberClickListener);
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

        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastNumeric || number.isEmpty() || lastOperator) {
                    number += "sin("; // Allow sin after a number or operator
                    tv_operations.setText(number);
                    lastNumeric = false; // Reset lastNumeric because we're adding a function
                    lastOperator = false; // Reset lastOperator
                }
            }
        });

        btn_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastNumeric || number.isEmpty() || lastOperator) {
                    number += "cos("; // Allow cos after a number or operator
                    tv_operations.setText(number);
                    lastNumeric = false; // Reset lastNumeric
                    lastOperator = false; // Reset lastOperator
                }
            }
        });

        btn_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastNumeric || number.isEmpty() || lastOperator) {
                    number += "tan("; // Allow tan after a number or operator
                    tv_operations.setText(number);
                    lastNumeric = false; // Reset lastNumeric
                    lastOperator = false; // Reset lastOperator
                }
            }
        });

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
                String operator = button.getText().toString();

                if (operator.equals("-")) {
                    if (number.isEmpty() || !lastNumeric) {
                        number += "-";
                        lastNumeric = false;
                        lastOperator = false;
                    } else if (lastNumeric && !lastOperator) {
                        number += operator;
                        lastNumeric = false;
                        lastOperator = true;
                    }
                } else {
                    if (lastNumeric) {
                        number += operator;
                        lastNumeric = false;
                        lastOperator = true;
                        lastDot = false;
                    } else if (lastOperator) {
                        number = number.substring(0, number.length() - 1) + operator;
                    }
                }

                tv_operations.setText(number);
            }
        };

        // Set listeners for the operations
        btn_plus.setOnClickListener(operationClickListener);
        btn_minus.setOnClickListener(operationClickListener);
        btn_multiply.setOnClickListener(operationClickListener);
        btn_divide.setOnClickListener(operationClickListener);
        btn_modulus.setOnClickListener(operationClickListener);

        // Adding listeners for new operations
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastNumeric || lastOperator || number.isEmpty()) {
                    number += "log(";
                    tv_operations.setText(number);
                    lastNumeric = false;
                    lastOperator = false;
                }
            }
        });

        btn_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastNumeric || lastOperator || number.isEmpty()) {
                    number += "√"; // Use the symbol for square root
                    tv_operations.setText(number);
                    lastNumeric = false;
                    lastOperator = false;
                }
            }
        });

        btn_pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric) {
                    number += "^";
                    tv_operations.setText(number);
                    lastNumeric = false;
                    lastOperator = true;
                }
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch HistoryActivity
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putStringArrayListExtra("history", history);  // Pass the history to HistoryActivity
                startActivityForResult(intent, REQUEST_CODE_HISTORY);  // Start activity for result
            }
        });
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Load saved history
        loadHistory();

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String originalExpression = tv_operations.getText().toString();  // Store the user-friendly expression

                if (lastNumeric) {
                    try {
                        // Modify the expression handling for trigonometric and log functions
                        String expression = originalExpression
                                .replaceAll("sin\\(([^)]+)\\)", "Math.sin(Math.PI / 180 * ($1))")
                                .replaceAll("cos\\(([^)]+)\\)", "(($1 % 360 == 90 || $1 % 360 == 270) ? 0 : Math.cos(Math.PI / 180 * ($1)))")
                                .replaceAll("tan\\(([^)]+)\\)", "($1 == 45 ? 1 : (($1 % 180 == 90) ? 'undefined' : Math.tan(Math.PI / 180 * ($1))))")
                                .replaceAll("log\\(([^)]+)\\)", "Math.log10($1)")
                                .replaceAll("√(\\d+)", "Math.sqrt($1)")
                                .replaceAll("(\\d+)\\^(\\d+)", "Math.pow($1, $2)")
                                .replace("x", "*");

                        // Handle division by zero
                        if (expression.contains("/0")) {
                            tv_result.setText("Error");
                            return;
                        }

                        // Use Rhino JavaScript engine for evaluation
                        Context rhino = Context.enter();
                        rhino.setOptimizationLevel(-1);
                        try {
                            org.mozilla.javascript.Scriptable scope = rhino.initStandardObjects();
                            Object result = rhino.evaluateString(scope, expression, "JavaScript", 1, null);

                            if (result != null && !result.toString().equals("undefined")) {
                                double doubleResult = Double.parseDouble(result.toString());

                                // Check if the result is NaN or Infinity
                                if (Double.isNaN(doubleResult) || Double.isInfinite(doubleResult)) {
                                    tv_result.setText("Error");
                                } else {
                                    // Check if the result is a whole number or a decimal
                                    if (doubleResult == (long) doubleResult) {
                                        // If the result is an integer, display it without decimals
                                        tv_result.setText(String.valueOf((long) doubleResult));
                                    } else {
                                        // Otherwise, display the result as a double
                                        tv_result.setText(String.valueOf(doubleResult));
                                    }

                                    // Store the original expression and result in history
                                    String historyEntry = originalExpression + " = " + tv_result.getText().toString();
                                    history.add(historyEntry);

                                    // Save the updated history to SharedPreferences
                                    saveHistory();
                                }
                            } else {
                                tv_result.setText("Error");
                            }
                        } finally {
                            Context.exit();
                        }

                    } catch (Exception e) {
                        Log.e("CalculatorError", "Error evaluating expression: " + e.getMessage());
                        tv_result.setText("Error");
                    }
                }
            }
        });




        btn_delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = "";
                tv_operations.setText("");
                tv_result.setText("");
                lastNumeric = false;
                lastOperator = false;
                lastDot = false;
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!number.isEmpty()) {
                    // Check if the last part of the number is a function (sin, cos, tan)
                    if (number.endsWith("sin(")) {
                        number = number.substring(0, number.length() - 4);  // Remove 'sin('
                    } else if (number.endsWith("cos(")) {
                        number = number.substring(0, number.length() - 4);  // Remove 'cos('
                    } else if (number.endsWith("tan(")) {
                        number = number.substring(0, number.length() - 4);  // Remove 'tan('
                    }else if (number.endsWith("log(")) {
                        number = number.substring(0, number.length() - 4);
                    } else if (!number.isEmpty()) {
                        // Otherwise, just delete the last character
                        number = number.substring(0, number.length() - 1);
                    }

                    tv_operations.setText(number);  // Update the display
                    lastNumeric = false;
                    lastOperator = false;
                }
            }
        });

    }

    private void loadHistory() {
        history.clear();
        String savedHistory = sharedPreferences.getString(KEY_HISTORY, "");
        Log.d("History", "Loaded history: " + savedHistory); // Log the loaded history
        if (!savedHistory.isEmpty()) {
            String[] entries = savedHistory.split(";");
            for (String entry : entries) {
                if (!entry.trim().isEmpty()) {
                    history.add(entry);
                }
            }
        }
    }

    private void saveHistory() {
        StringBuilder sb = new StringBuilder();
        for (String entry : history) {
            sb.append(entry).append(";");
        }
        editor.putString(KEY_HISTORY, sb.toString());
        editor.apply();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_HISTORY && resultCode == RESULT_OK) {
            if (data != null) {
                // Check if the user selected an operation from the history
                if (data.hasExtra("selected_operation")) {
                    String selectedOperation = data.getStringExtra("selected_operation");

                    if (selectedOperation != null) {
                        // Set the selected operation to tv_operations
                        number = selectedOperation;  // Set the operation back into the number variable
                        tv_operations.setText(number);  // Display the operation

                        // Adjust the flags as necessary
                        lastNumeric = true;  // Assuming the last part of the operation is numeric
                        lastOperator = false;  // Adjust flags accordingly
                    }

                } else if (data.hasExtra("history")) {
                    // Get the updated (cleared) history from HistoryActivity
                    ArrayList<String> updatedHistory = data.getStringArrayListExtra("history");
                    if (updatedHistory != null) {
                        history.clear();
                        history.addAll(updatedHistory);  // Update the local history in MainActivity
                        saveHistory();  // Save the updated history (empty or new)
                    }
                }
            }
        }
    }


}
