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

public class RectangularPrismVolumeActivity extends AppCompatActivity {

    private EditText inputLength;
    private EditText inputWidth;
    private EditText inputHeight;
    private Button buttonCalculateRectPrism;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangular_prism_volume);

        // Initialize views
        inputLength = findViewById(R.id.input_length);
        inputWidth = findViewById(R.id.input_width);
        inputHeight = findViewById(R.id.input_height);
        buttonCalculateRectPrism = findViewById(R.id.button_calculate_rect_prism);
        textResult = findViewById(R.id.text_result);

        // Set window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set button click listener
        buttonCalculateRectPrism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRectPrismVolume();
            }
        });
    }

    // Method to calculate the volume of a rectangular prism
    private void calculateRectPrismVolume() {
        String lengthStr = inputLength.getText().toString();
        String widthStr = inputWidth.getText().toString();
        String heightStr = inputHeight.getText().toString();

        if (lengthStr.isEmpty() || widthStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter all dimensions", Toast.LENGTH_SHORT).show();
            return;
        }

        double length = Double.parseDouble(lengthStr);
        double width = Double.parseDouble(widthStr);
        double height = Double.parseDouble(heightStr);
        double volume = length * width * height;  // Rectangular Prism Volume = length * width * height

        textResult.setText("Volume: " + volume);
    }
}
