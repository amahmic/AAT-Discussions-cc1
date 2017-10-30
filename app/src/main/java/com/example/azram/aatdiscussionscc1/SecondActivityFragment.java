package com.example.azram.aatdiscussionscc1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondActivityFragment extends Fragment {

    private static final String LOG_TAG = SecondActivityFragment.class.getName();

    public static final String RESULT_ID_KEY = "second-activity-result-id";
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.fragmentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent returnIntent = new Intent();
                Realm realm = null;
                try {
                    realm = Realm.getDefaultInstance();
                    final SecondActivityResult secondActivityResult = new SecondActivityResult(RESULT_ID, getString(R.string.returnedMessageFromFragment), true);
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.insertOrUpdate(secondActivityResult);
                            returnIntent.putExtra(RESULT_ID_KEY, RESULT_ID);
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
