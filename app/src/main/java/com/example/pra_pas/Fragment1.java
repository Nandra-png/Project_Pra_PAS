package com.example.pra_pas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pra_pas.api_frag1.ApiClient;
import com.example.pra_pas.api_frag1.ApiService;
import com.example.pra_pas.api_frag1.Team;
import com.example.pra_pas.api_frag1.TeamAdapter;
import com.example.pra_pas.api_frag1.TeamResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment1 extends Fragment {


    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView teamListRecyclerView;
    private TeamAdapter teamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment1, container, false);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<TeamResponse> call = apiService.getTeams("English Premier League");


        teamListRecyclerView = view.findViewById(R.id.teamListRecyclerView);
        teamListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Team> teams = response.body().getTeams();
                    teamAdapter = new TeamAdapter(getContext(), teams);
                    teamListRecyclerView.setAdapter(teamAdapter);
                } else {
                    Log.e(TAG, "Response not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e(TAG, "Failed to fetch teams: " + t.getMessage());
            }
        });








        return view;


    }


}