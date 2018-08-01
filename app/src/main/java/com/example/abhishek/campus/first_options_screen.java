package com.example.abhishek.campus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class first_options_screen extends AppCompatActivity {

    private Button Runner, Requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_options_screen);

        Runner = findViewById(R.id.runner);
        Requester = findViewById(R.id.requester);

        Runner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(first_options_screen.this, runner_options_screen.class);
                first_options_screen.this.startActivity(myIntent);
            }
        });

        Requester.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(first_options_screen.this, requester_options_screen.class);
                first_options_screen.this.startActivity(myIntent);
            }
        });


    }
}
