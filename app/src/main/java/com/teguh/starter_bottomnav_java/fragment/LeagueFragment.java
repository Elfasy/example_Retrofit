package com.teguh.starter_bottomnav_java.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teguh.starter_bottomnav_java.R;
import com.teguh.starter_bottomnav_java.network.Api;
import com.teguh.starter_bottomnav_java.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeagueFragment extends Fragment {


    public LeagueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Api api = RetrofitInstance.getRetrofitInstance().create(Api.class);
        Call<LeagueList> call = api.getAllLeagues("England", "Soccer");
        call.enqueue(new Callback<LeagueList>() {
            @Override
            public void onResponse(@NonNull Call<LeagueList> call, @NonNull Response<LeagueList> response) {
//                Toast.makeText(getContext(), "Data masuk", Toast.LENGTH_LONG).show();
                assert response.body() != null;
                generateLeague(response.body().getLeagues());
            }

            @Override
            public void onFailure(@NonNull Call<LeagueList> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false);
    }

    private void generateLeague(ArrayList<League> leagues) {
        RecyclerView recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_league);

        LeagueAdapter adapter = new LeagueAdapter(leagues);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


    }


}
