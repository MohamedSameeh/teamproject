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

public class CubeVolumeActivity extends AppCompatActivity {

    private EditText inputEdgeLength;
    private Button buttonCalculateCube;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_volume);

        // Initialize views
        inputEdgeLength = findViewById(R.id.input_edge_length);
        buttonCalculateCube = findViewById(R.id.button_calculate_cube);
        textResult = findViewById(R.id.text_result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set button click listener
        buttonCalculateCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCubeVolume();
            }
        });
    }

    // Method to calculate the volume of a cube
    private void calculateCubeVolume() {
        String edgeLengthStr = inputEdgeLength.getText().toString();

        if (edgeLengthStr.isEmpty()) {
            Toast.makeText(this, "Please enter the edge length", Toast.LENGTH_SHORT).show();
            return;
        }

        double edgeLength = Double.parseDouble(edgeLengthStr);
        double volume = Math.pow(edgeLength, 3);  // Cube Volume = edge³

        textResult.setText("Volume: " + volume);
    }
}
