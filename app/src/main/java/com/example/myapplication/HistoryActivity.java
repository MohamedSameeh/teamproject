package com.example.myapplication;

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
                history.clear();  // Clear the history list
                historyContainer.removeAllViews();  // Clear all displayed history entries

                // Clear history in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("calc_history_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();  // Clear all data in SharedPreferences
                editor.apply();  // Apply changes
            }
        });
    }

    // Method to display history entries dynamically
    private void displayHistory() {
        for (String entry : history) {
            TextView historyEntry = new TextView(this);
            historyEntry.setText(entry);
            historyEntry.setTextSize(18);
            historyEntry.setPadding(10, 10, 10, 10);
            historyContainer.addView(historyEntry);
        }
    }
}
