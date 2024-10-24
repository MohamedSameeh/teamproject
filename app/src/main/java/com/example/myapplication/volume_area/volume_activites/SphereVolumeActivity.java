package com.example.myapplication.volume_area.volume_activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class SphereVolumeActivity extends AppCompatActivity {

    private EditText inputRadius;
    private Button buttonCalculateSphere;
    private TextView textResultSphere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sphere_volume);

        // Initialize views
        inputRadius = findViewById(R.id.input_radius);
        buttonCalculateSphere = findViewById(R.id.button_calculate_sphere);
        textResultSphere = findViewById(R.id.text_result_sphere);

        // Set window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set button click listener
        buttonCalculateSphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSphereVolume();
            }
        });
    }

    // Method to calculate the volume of a sphere
    private void calculateSphereVolume() {
        String radiusStr = inputRadius.getText().toString();

        if (radiusStr.isEmpty()) {
            Toast.makeText(this, "Please enter the radius", Toast.LENGTH_SHORT).show();
            return;
        }

        double radius = Double.parseDouble(radiusStr);
        double volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);  // Sphere Volume = (4/3) * π * r^3

        textResultSphere.setText("Volume: " + volume);
    }
}
