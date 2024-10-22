package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout historyContainer;
    private Button btnClearHistory;
    private ArrayList<String> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyContainer = findViewById(R.id.history_container);
        btnClearHistory = findViewById(R.id.btn_clear_history);

        // Get the history passed from MainActivity
        history = getIntent().getStringArrayListExtra("history");

        // Display the history in user-friendly format
        if (history != null) {
            displayHistory();
        }

        // Clear history button functionality
        btnClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the history list
                history.clear();
                // Clear all displayed history entries
                historyContainer.removeAllViews();

                // Clear history in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("calc_history_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("history", ""); // Set history to an empty string
                editor.apply();  // Apply changes

                // Send the cleared history back to MainActivity
                Intent intent = new Intent();
                intent.putStringArrayListExtra("history", history);  // Pass back the cleared history
                setResult(RESULT_OK, intent);  // Set result OK to notify MainActivity
                finish();  // Close HistoryActivity and return to MainActivity
            }
        });


    }

    // Method to display history entries dynamically
    private void displayHistory() {
        for (final String entry : history) {
            TextView historyEntry = new TextView(this);
            historyEntry.setText(entry);
            historyEntry.setTextSize(22);
            historyEntry.setPadding(10, 15, 10, 10);
            historyEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("selected_operation", entry.split(" = ")[0]);  // Pass back only the operation (before "=")
                    setResult(RESULT_OK, intent);  // Set result OK to notify MainActivity
                    finish();  // Close the HistoryActivity and return to MainActivity
                }
            });

            historyContainer.addView(historyEntry);
        }
    }
}
