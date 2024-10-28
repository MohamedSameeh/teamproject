package com.example.myapplication.volume_area.equations;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.text.DecimalFormat;

public class HigherDegreeActivity extends AppCompatActivity {

    private EditText inputA, inputB, inputC, inputD;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_degree);

        inputA = findViewById(R.id.input_a);
        inputB = findViewById(R.id.input_b);
        inputC = findViewById(R.id.input_c);
        inputD = findViewById(R.id.input_d);
        resultText = findViewById(R.id.result_text);

        Button solveButton = findViewById(R.id.solve_button);
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveCubicEquation();
            }
        });
    }

    private void solveCubicEquation() {
        // Check for empty fields
        if (inputA.getText().toString().isEmpty() ||
                inputB.getText().toString().isEmpty() ||
                inputC.getText().toString().isEmpty() ||
                inputD.getText().toString().isEmpty()) {

            resultText.setText("Please fill in all fields.");
            return;
        }

        // Parse inputs as doubles after confirming all fields are non-empty
        double a = Double.parseDouble(inputA.getText().toString());
        double b = Double.parseDouble(inputB.getText().toString());
        double c = Double.parseDouble(inputC.getText().toString());
        double d = Double.parseDouble(inputD.getText().toString());

        // Solve the equation and display the result
        String result = calculateCubicRoots(a, b, c, d);
        resultText.setText(result);
    }

    private String calculateCubicRoots(double a, double b, double c, double d) {
        if (a == 0) {
            return "The coefficient 'a' cannot be zero in a cubic equation.";
        }

        // Normalize coefficients
        b /= a;
        c /= a;
        d /= a;

        double Q = (3 * c - Math.pow(b, 2)) / 9;
        double R = (9 * b * c - 27 * d - 2 * Math.pow(b, 3)) / 54;
        double D = Math.pow(Q, 3) + Math.pow(R, 2); // Discriminant

        DecimalFormat df = new DecimalFormat("#.######");
        StringBuilder result = new StringBuilder("Roots:\n");

        if (D > 0) { // One real root and two complex roots
            double S = Math.cbrt(R + Math.sqrt(D));
            double T = Math.cbrt(R - Math.sqrt(D));

            // Real root
            double realRoot = -b / 3 + (S + T);
            result.append("Real root: ").append(df.format(realRoot)).append("\n");

            // Complex roots
            double realPart = -b / 3 - (S + T) / 2;
            double imaginaryPart = Math.sqrt(3) * (S - T) / 2;

            result.append("Complex root 1: ")
                    .append(df.format(realPart))
                    .append(" + ")
                    .append(df.format(imaginaryPart))
                    .append("i\n");

            result.append("Complex root 2: ")
                    .append(df.format(realPart))
                    .append(" - ")
                    .append(df.format(imaginaryPart))
                    .append("i\n");

        } else if (D == 0) { // All roots real, at least two are equal
            double S = Math.cbrt(R);
            double realRoot1 = -b / 3 + 2 * S;
            double realRoot2 = -b / 3 - S;

            result.append("Real root 1: ").append(df.format(realRoot1)).append("\n");
            result.append("Real root 2: ").append(df.format(realRoot2)).append(" (double root)\n");

        } else { // D < 0, all roots real and distinct
            double theta = Math.acos(R / Math.sqrt(-Math.pow(Q, 3)));
            double sqrtQ = Math.sqrt(-Q);

            double realRoot1 = 2 * sqrtQ * Math.cos(theta / 3) - b / 3;
            double realRoot2 = 2 * sqrtQ * Math.cos((theta + 2 * Math.PI) / 3) - b / 3;
            double realRoot3 = 2 * sqrtQ * Math.cos((theta + 4 * Math.PI) / 3) - b / 3;

            result.append("Real root 1: ").append(df.format(realRoot1)).append("\n");
            result.append("Real root 2: ").append(df.format(realRoot2)).append("\n");
            result.append("Real root 3: ").append(df.format(realRoot3)).append("\n");
        }

        return result.toString();
    }

}
