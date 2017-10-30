package com.example.azram.aatdiscussionscc1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.azram.aatdiscussionscc1.data.Example;
import com.example.azram.aatdiscussionscc1.data.Root;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * pokrece SecondActivity sa ovim kodom
     * SecondActivityFragment treba pri povratu da postavi {@link SecondActivityResult} objekat (i da stavi valid na true)
     * i da vrati njegov id u intentu extra id sa key: SecondActivityFragment.RESULT_ID_KEY
     */
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;
    private SecondActivityResult secondActivityResult = null;

    private AlertDialog alertDialog;

    private AlertDialog alertDialogFromFragment;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        alertDialog = new AlertDialog.Builder(this).setMessage(getString(R.string.dialogMessage)).create();

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
                alertDialog.show();
            }
        });

        findViewById(R.id.sendPostButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://58489054.ngrok.io/")
                        .build();

                ExampleApiService service = retrofit.create(ExampleApiService.class);

                Call<Void> postRoot = service.postRoot(new Example(new Root("azra")));
                postRoot.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i(LOG_TAG, "ON RESPONSE");
                        if (response.code() == 200) {
                            Log.i(LOG_TAG, "200");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i(LOG_TAG, "ON FAILURE");

                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(LOG_TAG, "onActivityResult");

        if(requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    int result_id;
                    if (data != null) {
                        result_id = data.getIntExtra(SecondActivityFragment.RESULT_ID_KEY, SecondActivityResult.INVALID_ID);
                        if (result_id != SecondActivityResult.INVALID_ID) {
                            Log.i(LOG_TAG, "RESULT_ID_KEY: " + result_id);
                            secondActivityResult = realm.where(SecondActivityResult.class).equalTo("id", result_id).findFirst();
                            Log.i(LOG_TAG, "Upisao u secondActivityResult: " + String.valueOf(secondActivityResult != null && secondActivityResult.isValid()));
                        }
                    }
                    break;
                default:
                    Log.i(LOG_TAG, "Primio rezultat: " + resultCode);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "on Resume");
        if (secondActivityResult != null && secondActivityResult.isValid()) {

            alertDialogFromFragment = new AlertDialog.Builder(this)
                    .setMessage(secondActivityResult.getMessage())
                    .create();

            alertDialogFromFragment.show();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.where(SecondActivityResult.class).equalTo("id", secondActivityResult.getId()).findFirst().deleteFromRealm();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
        //da ne leak - a alert dialog
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        //alerDialogFromFragment se kreira u onResume samo ako se primi poruka od SecondActivityFragment
        //da ne leak - a alert dialog
        if (alertDialogFromFragment != null && alertDialogFromFragment.isShowing()) {
            alertDialogFromFragment.dismiss();
        }
    }
}
