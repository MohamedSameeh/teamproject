package com.example.myapplication.volume_area;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.volume_area.area_activites.circle_area_activity;
import com.example.myapplication.volume_area.area_activites.parallelogram_area_activity;
import com.example.myapplication.volume_area.area_activites.rectangle_area_activity;
import com.example.myapplication.volume_area.area_activites.square_calculation_activity;
import com.example.myapplication.volume_area.area_activites.triangle_area_activity;

public class areaActivity extends AppCompatActivity {
   Button btn_square,btn_rectangle,btn_parallelogram,btn_circle,btn_triangle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        btn_square=findViewById(R.id.button_square);
        btn_rectangle=findViewById(R.id.button_rectangle);
        btn_parallelogram=findViewById(R.id.button_parallelogram);
        btn_circle=findViewById(R.id.button_circle);
        btn_triangle=findViewById(R.id.button_triangle);
        btn_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(areaActivity.this, square_calculation_activity.class);
                startActivity(i);
            }
        });

        btn_rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(areaActivity.this, rectangle_area_activity.class);
                startActivity(i);
            }
        });
        btn_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(areaActivity.this, circle_area_activity.class);
                startActivity(i);
            }
        });
        btn_triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(areaActivity.this, triangle_area_activity.class);
                startActivity(i);
            }
        });
        btn_parallelogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(areaActivity.this, parallelogram_area_activity.class);
                startActivity(i);
            }
        });
    }
}