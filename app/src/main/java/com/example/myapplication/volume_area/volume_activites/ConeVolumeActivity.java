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

public class ConeVolumeActivity extends AppCompatActivity {

    private EditText inputRadius, inputHeight;
    private Button buttonCalculateCone;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cone_volume);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        inputRadius = findViewById(R.id.input_radius);
        inputHeight = findViewById(R.id.input_height);
        buttonCalculateCone = findViewById(R.id.button_calculate_cone);
        textResult = findViewById(R.id.text_result);

        // Set button click listener
        buttonCalculateCone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateVolume();
            }
        });
    }

    // Method to calculate the volume of a cone
    private void calculateVolume() {
        String radiusStr = inputRadius.getText().toString();
        String heightStr = inputHeight.getText().toString();

        if (radiusStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both radius and height", Toast.LENGTH_SHORT).show();
            return;
        }

        double radius = Double.parseDouble(radiusStr);
        double height = Double.parseDouble(heightStr);
        double volume = (Math.PI * radius * radius * height) / 3; // Cone Volume = (πr²h) / 3

        textResult.setText("Volume: " + volume);
    }
}
