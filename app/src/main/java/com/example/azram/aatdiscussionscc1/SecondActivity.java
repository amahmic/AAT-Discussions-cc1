package com.example.azram.aatdiscussionscc1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private static final String FRAGMENT_VIEW_ID = "second-activity-fragment";

    Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        if (savedInstanceState == null) {
            mFragment = new SecondActivityFragment();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, mFragment, FRAGMENT_VIEW_ID).commit();
        }
    }
}
