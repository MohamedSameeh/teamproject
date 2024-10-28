package com.example.myapplication.volume_area.equations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class equations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equations);

        Button buttonFirstDegree = findViewById(R.id.buttonFirstDegree);
        Button buttonSecondDegree = findViewById(R.id.buttonSecondDegree);
        Button buttonThirdDegree = findViewById(R.id.buttonThirdDegree);
        Button buttonHigherDegree = findViewById(R.id.buttonHigherDegree);

        buttonFirstDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(equations.this, FirstDegreeActivity.class));
            }
        });

        buttonSecondDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(equations.this, SecondDegreeActivity.class));
            }
        });

        buttonThirdDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(equations.this, ThirdDegreeActivity.class));
            }
        });

        buttonHigherDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(equations.this, HigherDegreeActivity.class));
            }
        });
    }
}
