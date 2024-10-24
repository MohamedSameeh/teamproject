package com.example.myapplication.volume_area.volume_activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class CylinderVolumeActivity extends AppCompatActivity {

    private EditText inputRadius, inputHeight;
    private Button buttonCalculateCylinder;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cylinder_volume);

        // Initialize the views
        inputRadius = findViewById(R.id.input_radius);
        inputHeight = findViewById(R.id.input_height);
        buttonCalculateCylinder = findViewById(R.id.button_calculate_cylinder);
        textResult = findViewById(R.id.text_result);

        // Set button click listener
        buttonCalculateCylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateVolume();
            }
        });
    }

    // Method to calculate the volume of a cylinder
    private void calculateVolume() {
        String radiusStr = inputRadius.getText().toString();
        String heightStr = inputHeight.getText().toString();

        if (radiusStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both radius and height", Toast.LENGTH_SHORT).show();
            return;
        }

        double radius = Double.parseDouble(radiusStr);
        double height = Double.parseDouble(heightStr);
        double volume = Math.PI * radius * radius * height; // Cylinder Volume = πr²h

        textResult.setText("Volume: " + volume);
    }
}
