package com.example.myapplication.volume_area.equations;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class ThirdDegreeActivity extends AppCompatActivity {

    private EditText editTextA, editTextB, editTextC;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_degree);

        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);
        textViewResult = findViewById(R.id.textViewResultThirdDegree);
        Button buttonCalculate = findViewById(R.id.buttonCalculateThirdDegree);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateThirdDegree();
            }
        });
    }

    private void calculateThirdDegree() {
        double a = Double.parseDouble(editTextA.getText().toString());
        double b = Double.parseDouble(editTextB.getText().toString());
        double c = Double.parseDouble(editTextC.getText().toString());

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            // Real roots
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            textViewResult.setText("Roots: x1 = " + x1 + ", x2 = " + x2);
        } else if (discriminant == 0) {
            // One real root
            double x = -b / (2 * a);
            textViewResult.setText("One root: x = " + x);
        } else {
            // Complex roots
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            textViewResult.setText("Complex roots:\n x1 = " + realPart + " + " + imaginaryPart + "i\n x2 = " + realPart + " - " + imaginaryPart + "i");
        }
    }
}
