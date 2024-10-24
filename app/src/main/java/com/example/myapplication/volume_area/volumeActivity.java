package com.example.myapplication.volume_area;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.volume_area.volume_activites.ConeVolumeActivity;
import com.example.myapplication.volume_area.volume_activites.CubeVolumeActivity;
import com.example.myapplication.volume_area.volume_activites.CylinderVolumeActivity;
import com.example.myapplication.volume_area.volume_activites.RectangularPrismVolumeActivity;
import com.example.myapplication.volume_area.volume_activites.SphereVolumeActivity;

public class volumeActivity extends AppCompatActivity {

    private Button buttonCylinder, buttonSphere, buttonCube, buttonCone, buttonRectangularPrism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        // Initialize buttons
        buttonCylinder = findViewById(R.id.button_cylinder);
        buttonSphere = findViewById(R.id.button_sphere);
        buttonCube = findViewById(R.id.button_cube);
        buttonCone = findViewById(R.id.button_cone);
        buttonRectangularPrism = findViewById(R.id.button_rectangular_prism);

        // Set click listeners for buttons
        buttonCylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(volumeActivity.this, CylinderVolumeActivity.class));
            }
        });

        buttonSphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(volumeActivity.this, SphereVolumeActivity.class));
            }
        });

        buttonCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(volumeActivity.this, CubeVolumeActivity.class));
            }
        });

        buttonCone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(volumeActivity.this, ConeVolumeActivity.class));
            }
        });

        buttonRectangularPrism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(volumeActivity.this, RectangularPrismVolumeActivity.class));
            }
        });
    }
}
