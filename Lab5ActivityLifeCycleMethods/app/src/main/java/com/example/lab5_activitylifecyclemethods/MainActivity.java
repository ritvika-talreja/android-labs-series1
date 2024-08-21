package com.example.lab5_activitylifecyclemethods;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the views
        View revealView = findViewById(R.id.revealView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView registerNumberTextView = findViewById(R.id.registerNumberTextView);

        // Wait for the layout to be fully drawn before starting the animation
        revealView.post(() -> startCircularReveal(revealView, nameTextView, registerNumberTextView));
    }

    private void startCircularReveal(View revealView, TextView nameTextView, TextView registerNumberTextView) {
        // Get the center for the clipping circle
        int cx = revealView.getWidth() / 2;
        int cy = revealView.getHeight() / 2;

        // Get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // Create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0f, finalRadius);
        circularReveal.setDuration(1000);
        circularReveal.setInterpolator(new AccelerateInterpolator());

        // Make the reveal view visible and start the animation
        revealView.setVisibility(View.VISIBLE);
        circularReveal.start();

        // Fade in the text views after the circular reveal animation
        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                nameTextView.setVisibility(View.VISIBLE);
                nameTextView.setAlpha(0f);
                nameTextView.animate().alpha(1f).setDuration(800);

                registerNumberTextView.setVisibility(View.VISIBLE);
                registerNumberTextView.setAlpha(0f);
                registerNumberTextView.animate().alpha(1f).setDuration(800);
            }

            @Override
            public void onAnimationStart(Animator animation) { }

            @Override
            public void onAnimationCancel(Animator animation) { }

            @Override
            public void onAnimationRepeat(Animator animation) { }
        });

        // Transition to the main activity after the animation ends
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // Adjust the delay as needed
    }
}
