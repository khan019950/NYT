package com.example.nyt.utils;


import com.example.nyt.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//Interface for to make API calls via retrofit lib

public interface ApiInterface {

    //GET call for top technological articles. using observable here
    @GET("technology.json")
    Call<News> getArticles(
            @Query("api-key") String apiKey
    );
}
