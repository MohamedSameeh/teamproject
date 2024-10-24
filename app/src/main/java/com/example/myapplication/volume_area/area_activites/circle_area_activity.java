package com.example.myapplication.volume_area.area_activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class circle_area_activity extends AppCompatActivity {

    private EditText inputRadius;
    private TextView resultArea;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_area);

        // Initialize views
        inputRadius = findViewById(R.id.input_radius);
        resultArea = findViewById(R.id.result_area_circle);
        calculateButton = findViewById(R.id.button_calculate_circle);

        // Set click listener for calculating circle area
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCircleArea();
            }
        });
    }

    private void calculateCircleArea() {
        String radiusStr = inputRadius.getText().toString();
        if (!radiusStr.isEmpty()) {
            double radius = Double.parseDouble(radiusStr);
            double area = Math.PI * radius * radius;
            resultArea.setText(String.format("Area: %.2f", area));
        } else {
            Toast.makeText(circle_area_activity.this, "Please enter a radius", Toast.LENGTH_SHORT).show();
        }
    }
}
