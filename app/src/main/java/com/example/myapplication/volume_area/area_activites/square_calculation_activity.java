package com.example.myapplication.volume_area.area_activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class square_calculation_activity extends AppCompatActivity {

    EditText editTextSide;
    TextView textViewResult;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_calculation);

        // Initialize UI elements
        editTextSide = findViewById(R.id.editTextSide);
        textViewResult = findViewById(R.id.textViewResult);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        // Set button click listener to calculate the area
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateArea();
            }
        });
    }

    private void calculateArea() {
        String sideText = editTextSide.getText().toString();

        // Check if input is valid
        if (sideText.isEmpty()) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse side value
            double side = Double.parseDouble(sideText);

            // Calculate area
            double area = side * side;

            // Display result
            textViewResult.setText(String.format("Area: %.2f", area));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}
