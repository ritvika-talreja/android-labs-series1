package com.example.lab5_activitylifecyclemethods;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast.makeText(this, "onCreate() Called", Toast.LENGTH_SHORT).show();

        // Setup the Floating Action Button (FAB)
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> {
            Toast.makeText(SecondActivity.this, "FAB Clicked!", Toast.LENGTH_SHORT).show();
            // Display the logs only when FAB is clicked
            updateLogcatOutput();
        });
    }

    private void updateLogcatOutput() {
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line).append("\n");
            }

            TextView logView = findViewById(R.id.textView4);
            logView.setText(log.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart() Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy() Called", Toast.LENGTH_SHORT).show();
    }
}
