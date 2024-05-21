package com.example.pra_pas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.pra_pas.api_frag2.League;
import com.example.pra_pas.api_frag2.LeagueAdapter;
import com.example.pra_pas.api_frag2.LeagueResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment2 extends Fragment {

    private static final String TAG = Fragment1.class.getSimpleName();

    private RecyclerView leagueListRecyclerView;
    private LeagueAdapter leagueAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        leagueListRecyclerView = view.findViewById(R.id.LeagueListRecyclerView);
        leagueListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<LeagueResponse> call = apiService.getLeagues();

        call.enqueue(new Callback<LeagueResponse>() {
            @Override
            public void onResponse(Call<LeagueResponse> call, Response<LeagueResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<League> leagues = response.body().getLeagues();
                    leagueAdapter = new LeagueAdapter(getContext(), leagues);
                    leagueListRecyclerView.setAdapter(leagueAdapter);
                } else {
                    Log.e(TAG, "Response not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LeagueResponse> call, Throwable t) {
                Log.e(TAG, "Failed to fetch leagues: " + t.getMessage());
            }
        });

        return view;
    }
}
