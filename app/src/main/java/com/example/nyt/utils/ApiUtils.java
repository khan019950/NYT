package com.example.nyt.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//helper kind of class for API

public class ApiUtils {
    public static String BASE_URL = "https://api.nytimes.com/svc/topstories/v2/";
    public static Retrofit retrofit;

    public static Retrofit getApiUtil(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}