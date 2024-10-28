package com.example.myapplication.volume_area.equations;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class FirstDegreeActivity extends AppCompatActivity {

    private EditText editTextA1, editTextB1, editTextC1;
    private EditText editTextA2, editTextB2, editTextC2;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_degree);

        // Initialize EditText fields
        editTextA1 = findViewById(R.id.editTextA1);
        editTextB1 = findViewById(R.id.editTextB1);
        editTextC1 = findViewById(R.id.editTextC1);
        editTextA2 = findViewById(R.id.editTextA2);
        editTextB2 = findViewById(R.id.editTextB2);
        editTextC2 = findViewById(R.id.editTextC2);

        textViewResult = findViewById(R.id.textViewResultFirstDegree);
        Button buttonCalculate = findViewById(R.id.buttonCalculateFirstDegree);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSystemOfEquations();
            }
        });
    }

    private void calculateSystemOfEquations() {
        try {
            // Parse input values as Complex numbers
            Complex a1 = Complex.parseComplex(editTextA1.getText().toString());
            Complex b1 = Complex.parseComplex(editTextB1.getText().toString());
            Complex c1 = Complex.parseComplex(editTextC1.getText().toString());
            Complex a2 = Complex.parseComplex(editTextA2.getText().toString());
            Complex b2 = Complex.parseComplex(editTextB2.getText().toString());
            Complex c2 = Complex.parseComplex(editTextC2.getText().toString());

            // Calculate the determinant using Complex arithmetic
            Complex determinant = a1.multiply(b2).subtract(a2.multiply(b1));

            // Check if the determinant is zero (i.e., no unique solution)
            if (determinant.real == 0 && determinant.imaginary == 0) {
                textViewResult.setText("No unique solution: The equations are either dependent or inconsistent.");
            } else {
                // Apply Cramer's rule using Complex arithmetic
                Complex determinantX = c1.multiply(b2).subtract(c2.multiply(b1));
                Complex determinantY = a1.multiply(c2).subtract(a2.multiply(c1));

                Complex x = determinantX.divide(determinant);
                Complex y = determinantY.divide(determinant);

                textViewResult.setText("Solution: X = " + x + ", Y = " + y);
            }
        } catch (NumberFormatException e) {
            textViewResult.setText("Please enter valid numbers in all fields.");
        }
    }

    public static class Complex {
        private double real;
        private double imaginary;

        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        // Parse method for Complex numbers (not static)
        public static Complex parseComplex(String s) {
            s = s.replaceAll(" ", "");
            String[] parts = s.split("(?=[+-])");
            double real = 0, imaginary = 0;

            for (String part : parts) {
                if (part.endsWith("i")) {
                    imaginary = Double.parseDouble(part.replace("i", ""));
                } else {
                    real = Double.parseDouble(part);
                }
            }
            return new Complex(real, imaginary);
        }

        // Complex addition
        public Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imaginary + other.imaginary);
        }

        // Complex subtraction
        public Complex subtract(Complex other) {
            return new Complex(this.real - other.real, this.imaginary - other.imaginary);
        }

        // Complex multiplication
        public Complex multiply(Complex other) {
            double realPart = this.real * other.real - this.imaginary * other.imaginary;
            double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;
            return new Complex(realPart, imaginaryPart);
        }

        // Complex division
        public Complex divide(Complex other) {
            double denominator = other.real * other.real + other.imaginary * other.imaginary;
            double realPart = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
            double imaginaryPart = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
            return new Complex(realPart, imaginaryPart);
        }

        @Override
        public String toString() {
            return real + (imaginary >= 0 ? "+" : "") + imaginary + "i";
        }
    }



}
