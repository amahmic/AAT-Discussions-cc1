package com.example.azram.aatdiscussionscc1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.azram.aatdiscussionscc1.api.ExampleApiService;
import com.example.azram.aatdiscussionscc1.api.data.PostRootWrapper;
import com.example.azram.aatdiscussionscc1.api.data.Root;
import com.example.azram.aatdiscussionscc1.api.data.AnswerWrapper;
import com.example.azram.aatdiscussionscc1.api.data.GetMePleaseWrapper;
import com.example.azram.aatdiscussionscc1.api.data.Obj2;
import com.example.azram.aatdiscussionscc1.api.data.ObjAnswer;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * pokrece SecondActivity sa ovim request kodom
     * SecondActivityFragment treba pri povratu da postavi {@link SecondActivityResult} objekat (i da stavi valid na true)
     * i da vrati njegov id u intentu extra id sa key: SecondActivityFragment.RESULT_ID_KEY
     */
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;

    private SecondActivityResult secondActivityResult = null;

    /**
     * {@link AlertDialog} koji se prikazuje klikom na dugme Button 2
     */
    private AlertDialog alertDialog;

    /**
     * {@link AlertDialog} koji se prikazuje nakon povratka iz {@link SecondActivity}
     */
    private AlertDialog alertDialogFromFragment;

    private Realm realm;

    private ExampleApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        findViewById(R.id.startSecondActivityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(secondActivity, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

        alertDialog = new AlertDialog.Builder(this).setMessage(getString(R.string.dialog_message)).create();
        findViewById(R.id.displayDialogMessageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ExampleApiService.BASE_URL)
                .build();

        service = retrofit.create(ExampleApiService.class);

        //Klikom na button posalji:
        /*
        {
	        "root": {
		        "non_root" : "azra"
	        }
         }

         -> PostRootWrapper(Root)
         */
        findViewById(R.id.sendPostButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Void> postRoot = service.postRoot(new PostRootWrapper(new Root("azra")));
                postRoot.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        Log.i(LOG_TAG, "onResponse postRoot with code: " + response.code());
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        Log.i(LOG_TAG, "onFailure postRoot");

                    }
                });
            }
        });

        //Klikom na getMePleaseButton posalji getMePlease
        /*
        {
            "obj": {
                "obj2": [
                    {
                        "obj3": string
                        "name": string
                        "age": double
                    }
                ]
         }
         -> GetMePleaseWrapper(Obj(List<Obj2>))
         */
        //Ukoliko dobije response sa statusom 200, posalji post
        findViewById(R.id.getMePleaseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.getMePlease().enqueue(new Callback<GetMePleaseWrapper>() {
                    @Override
                    public void onResponse(@NonNull Call<GetMePleaseWrapper> call, @NonNull Response<GetMePleaseWrapper> response) {
                        Log.i(LOG_TAG, "onResponse getMePlease with code: " + response.code());

                        /*
                        {
                            "obj": {
                                "obj2": {
                                    "obj3": string
                                    "name": string
                                    "age": double
                                }
                             }
                         }
                         -> AnswerWrapper(ObjAnswer(Obj2))
                         */
                        if (response.code() == 200) {
                            service.postAnswer(new AnswerWrapper(new ObjAnswer(new Obj2("AAT ludilo", "azra", 27)))).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                    Log.i(LOG_TAG, "onResponse postAnswer with code: " + response.code());
                                }

                                @Override
                                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                    Log.i(LOG_TAG, "onFailure postAnswer");
                                }
                            });
                        }
                        else {
                            Log.i(LOG_TAG, "onResponse getMePlease code != 200, postAnswer will not be called!");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GetMePleaseWrapper> call, @NonNull Throwable t) {
                        Log.i(LOG_TAG, "onFailure getMePlease");
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
                            Log.i(LOG_TAG, "id u bazi SecondActivityResult objekta vracenog iz SecondActivityFragmenta: " + result_id);
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
        Log.i(LOG_TAG, "onResume");
        if (secondActivityResult != null && secondActivityResult.isValid()) {
            alertDialogFromFragment = new AlertDialog.Builder(this)
                    .setMessage(secondActivityResult.getMessage())
                    .create();
            alertDialogFromFragment.show();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
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

        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }

        //alerDialogFromFragment se kreira u onResume samo ako se primi poruka od SecondActivityFragment
        if (alertDialogFromFragment != null && alertDialogFromFragment.isShowing()) {
            alertDialogFromFragment.dismiss();
        }
    }
}
