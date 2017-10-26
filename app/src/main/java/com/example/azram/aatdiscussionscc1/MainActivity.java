package com.example.azram.aatdiscussionscc1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertDialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.dialogMessage))
                .create();

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(secondActivity, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getAlertDialog(R.string.dialogMessageButton2).show();
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    //getAlertDialog(R.string.dialogMessageOnResult).show();
                    alertDialog.show();
                    break;
                default:
                    Log.i(LOG_TAG, "Primio rezultat: " + resultCode);
            }
        }
    }

    private AlertDialog.Builder getAlertDialog(int messageResId) {
        return new AlertDialog.Builder(this)
                .setMessage(getString(messageResId));
    }


}
