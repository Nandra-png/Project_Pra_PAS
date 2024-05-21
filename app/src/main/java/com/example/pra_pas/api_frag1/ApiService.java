package com.example.pra_pas.api_frag1;

import com.example.pra_pas.api_frag2.LeagueResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search_all_teams.php")
    Call<TeamResponse> getTeams(@Query("l") String league);

    @GET("all_leagues.php")
    Call<LeagueResponse> getLeagues();
}
