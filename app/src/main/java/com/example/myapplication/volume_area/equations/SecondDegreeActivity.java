package com.example.myapplication.volume_area.equations;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class SecondDegreeActivity extends AppCompatActivity {

    private EditText editTextA1, editTextB1, editTextC1, editTextD1;
    private EditText editTextA2, editTextB2, editTextC2, editTextD2;
    private EditText editTextA3, editTextB3, editTextC3, editTextD3;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_degree);

        editTextA1 = findViewById(R.id.editTextA1);
        editTextB1 = findViewById(R.id.editTextB1);
        editTextC1 = findViewById(R.id.editTextC1);
        editTextD1 = findViewById(R.id.editTextD1);

        editTextA2 = findViewById(R.id.editTextA2);
        editTextB2 = findViewById(R.id.editTextB2);
        editTextC2 = findViewById(R.id.editTextC2);
        editTextD2 = findViewById(R.id.editTextD2);

        editTextA3 = findViewById(R.id.editTextA3);
        editTextB3 = findViewById(R.id.editTextB3);
        editTextC3 = findViewById(R.id.editTextC3);
        editTextD3 = findViewById(R.id.editTextD3);

        textViewResult = findViewById(R.id.textViewResultLinearSystem);
        Button buttonSolve = findViewById(R.id.buttonSolveSystem);

        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveSystem();
            }
        });
    }

    private void solveSystem() {
        try {
            // Parse input values
            double a1 = Double.parseDouble(editTextA1.getText().toString());
            double b1 = Double.parseDouble(editTextB1.getText().toString());
            double c1 = Double.parseDouble(editTextC1.getText().toString());
            double d1 = Double.parseDouble(editTextD1.getText().toString());

            double a2 = Double.parseDouble(editTextA2.getText().toString());
            double b2 = Double.parseDouble(editTextB2.getText().toString());
            double c2 = Double.parseDouble(editTextC2.getText().toString());
            double d2 = Double.parseDouble(editTextD2.getText().toString());

            double a3 = Double.parseDouble(editTextA3.getText().toString());
            double b3 = Double.parseDouble(editTextB3.getText().toString());
            double c3 = Double.parseDouble(editTextC3.getText().toString());
            double d3 = Double.parseDouble(editTextD3.getText().toString());

            // Calculate determinant of the system
            double det = a1 * (b2 * c3 - b3 * c2) - b1 * (a2 * c3 - a3 * c2) + c1 * (a2 * b3 - a3 * b2);

            if (det != 0) {
                // Cramer's Rule for X, Y, and Z
                double x = (d1 * (b2 * c3 - b3 * c2) - b1 * (d2 * c3 - d3 * c2) + c1 * (d2 * b3 - d3 * b2)) / det;
                double y = (a1 * (d2 * c3 - d3 * c2) - d1 * (a2 * c3 - a3 * c2) + c1 * (a2 * d3 - a3 * d2)) / det;
                double z = (a1 * (b2 * d3 - b3 * d2) - b1 * (a2 * d3 - a3 * d2) + d1 * (a2 * b3 - a3 * b2)) / det;

                textViewResult.setText("Solution: X = " + x + ", Y = " + y + ", Z = " + z);
            } else {
                textViewResult.setText("No unique solution exists.");
            }
        } catch (NumberFormatException e) {
            textViewResult.setText("Please enter valid numbers for all coefficients.");
        }
    }
}
