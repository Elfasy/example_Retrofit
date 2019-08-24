package com.teguh.starter_bottomnav_java.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("api/v1/json/1/all_sports.php")
    Call<SportList> getAllSports();

    @GET("api/v1/json/1/search_all_leagues.php")
    Call<LeagueList> getAllLeagues(
            @Query("c") String c,
            @Query("s") String s
    );


}
