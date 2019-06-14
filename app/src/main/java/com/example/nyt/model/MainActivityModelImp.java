package com.example.nyt.model;


import com.example.nyt.contract.MainContract;
import com.example.nyt.presenter.MainActivityPresenterImp;
import com.example.nyt.utils.ApiInterface;
import com.example.nyt.utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityModelImp implements MainContract.Model {

    ApiInterface apiInterface = ApiUtils.getApiUtil().create(ApiInterface.class);
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MainActivityPresenterImp presenterImp;
    List<Article> mArticles = new ArrayList<>();

    public MainActivityModelImp(MainActivityPresenterImp presenterImp) {
        this.presenterImp = presenterImp;
    }

    @Override
    public void fetchData() {

        Call<News> call;
        call = apiInterface.getArticles("SLuQoGMFhmfDjjkwwH4lnJbvXS6fPlis");
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if (response.isSuccessful() && response.body().getArticles() != null) {
                    if (!mArticles.isEmpty()) {
                        mArticles.clear();
                    }
                    mArticles = response.body().getArticles();
                    presenterImp.onDataChanged(mArticles);

                }else {
                    presenterImp.fetchFailed();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                presenterImp.fetchFailed();
            }
        });
    }
}
