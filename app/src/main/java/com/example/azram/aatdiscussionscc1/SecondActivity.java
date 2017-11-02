package com.example.azram.aatdiscussionscc1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final String FRAGMENT_VIEW_TAG = "second-activity-fragment";

    SecondActivityFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity_second);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });*/

        mFragment = (SecondActivityFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_VIEW_TAG);

        if (mFragment == null) {
            mFragment = new SecondActivityFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mFragment, FRAGMENT_VIEW_TAG)
                    .commit();
        }
    }
}
