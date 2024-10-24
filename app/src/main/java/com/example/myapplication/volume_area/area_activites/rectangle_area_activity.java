package com.example.myapplication.volume_area.area_activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class rectangle_area_activity extends AppCompatActivity {

    private EditText inputLength, inputWidth;
    private TextView resultArea;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle_area);

        // Initialize views
        inputLength = findViewById(R.id.input_length);
        inputWidth = findViewById(R.id.input_width);
        resultArea = findViewById(R.id.result_area_rectangle);
        calculateButton = findViewById(R.id.button_calculate_rectangle);

        // Set click listener for calculating rectangle area
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRectangleArea();
            }
        });
    }

    private void calculateRectangleArea() {
        String lengthStr = inputLength.getText().toString();
        String widthStr = inputWidth.getText().toString();

        if (!lengthStr.isEmpty() && !widthStr.isEmpty()) {
            double length = Double.parseDouble(lengthStr);
            double width = Double.parseDouble(widthStr);
            double area = length * width;
            resultArea.setText(String.format("Area: %.2f", area));
        } else {
            Toast.makeText(rectangle_area_activity.this, "Please enter both length and width", Toast.LENGTH_SHORT).show();
        }
    }
}
