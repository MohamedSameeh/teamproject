package com.example.myapplication.volume_area.area_activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class parallelogram_area_activity extends AppCompatActivity {

    private EditText inputBase;
    private EditText inputHeight;
    private TextView resultArea;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallelogram_area);

        // Initialize views
        inputBase = findViewById(R.id.input_base);
        inputHeight = findViewById(R.id.input_height);
        resultArea = findViewById(R.id.result_area_parallelogram);
        calculateButton = findViewById(R.id.button_calculate_parallelogram);

        // Set click listener to calculate the area
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateParallelogramArea();
            }
        });
    }

    private void calculateParallelogramArea() {
        String baseStr = inputBase.getText().toString();
        String heightStr = inputHeight.getText().toString();

        if (!baseStr.isEmpty() && !heightStr.isEmpty()) {
            double base = Double.parseDouble(baseStr);
            double height = Double.parseDouble(heightStr);
            double area = base * height;
            resultArea.setText(String.format("Area: %.2f", area));
        } else {
            Toast.makeText(parallelogram_area_activity.this, "Please enter both base and height", Toast.LENGTH_SHORT).show();
        }
    }
}
