package com.example.myapplication.volume_area;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class volume_area_Activity extends AppCompatActivity {
   Button area_btn,volume_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_area);
        area_btn=findViewById(R.id.btn_area);
        volume_btn=findViewById(R.id.btn_volume);
        area_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(volume_area_Activity.this, areaActivity.class);
                startActivity(i);
            }
        });
        volume_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(volume_area_Activity.this, volumeActivity.class);
                startActivity(i);
            }
        });
    }
}