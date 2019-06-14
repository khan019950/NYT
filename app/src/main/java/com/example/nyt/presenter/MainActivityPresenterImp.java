package com.example.nyt.presenter;


import android.util.Log;

import com.example.nyt.contract.MainContract;
import com.example.nyt.model.Article;
import com.example.nyt.model.MainActivityModelImp;
import com.example.nyt.view.RecAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivityPresenterImp implements MainContract.Presenter {

    private List<Article> articles = new ArrayList<>();

    private MainActivityModelImp modelImp;
    private MainContract.View mView;
    public Article currArticle;

    public MainActivityPresenterImp(MainContract.View view) {
        modelImp = new MainActivityModelImp(this);
        this.mView = view;
    }

    @Override
    public void initData() {
        modelImp.fetchData();
    }

    @Override
    public void onBindViewAtPosition(RecAdapter.MyViewHolder holder, int position) {
        Log.d("Here", "Hreree");
        Article article = articles.get(position);
        currArticle = article;
        holder.setTitle(article.getTitle());
        holder.setDesc(article.getnAbstract());
        holder.setPublishedOn(str2DateFormat(article.getPublished_date()));
        holder.setAuthor(article.getByline());
        holder.setNewsImg(article.getMultimedia().get(article.getMultimedia().size()-1).getImgUrl());
        holder.setOnOpenClickListener(article);
        holder.setOnShareClickListener(article);
    }

    @Override
    public int getArticlesCount() {
        return articles.size();
    }

    public void intentActivity(int id, Article article){
        mView.startIntentActivity(id, article);
    }

    public void onDataChanged(List<Article> articlesData){
        this.articles = articlesData;
        mView.dataHasChanged();
    }

    @Override
    public void fetchFailed() {
        mView.hideLoader();
        mView.showNetworkErr();
    }

    // String to Date convertor
    public static String str2DateFormat(String oldstringDate){
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale("en"));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-K").parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }
}
