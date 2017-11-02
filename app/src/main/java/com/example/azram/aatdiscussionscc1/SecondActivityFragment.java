package com.example.azram.aatdiscussionscc1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondActivityFragment extends Fragment {

    private static final String LOG_TAG = SecondActivityFragment.class.getName();

    /**
     * Key za id {@link SecondActivityResult} objekta spasenog u bazi
     * Salje se u result intentu
     */
    public static final String RESULT_ID_KEY = "second-activity-result-id";

    /**
     * Id za {@link SecondActivityResult} objekat spasen u bazi
     */
    private static final int RESULT_ID = 1;

    public SecondActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_activity, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Na klik buttona spasi SecondActivityResult u bazu, dodaj njegov id u intent i finish activity
        getActivity().findViewById(R.id.fragmentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent returnIntent = new Intent();
                Realm realm = null;
                try {
                    realm = Realm.getDefaultInstance();
                    final SecondActivityResult secondActivityResult = new SecondActivityResult(RESULT_ID, getString(R.string.message_from_fragment), true);
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(@NonNull Realm realm) {
                            realm.insertOrUpdate(secondActivityResult);
                            returnIntent.putExtra(RESULT_ID_KEY, RESULT_ID);
                            Log.i(LOG_TAG, "Spasen: " + secondActivityResult.toString());
                        }
                    });
                }
                finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
                getActivity().setResult(Activity.RESULT_OK, returnIntent);
                getActivity().finish();
            }
        });
    }
}
