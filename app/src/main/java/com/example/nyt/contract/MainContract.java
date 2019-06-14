package com.example.nyt.contract;

import com.example.nyt.model.Article;
import com.example.nyt.view.RecAdapter;


public interface MainContract {
    interface View {
        void initView();
        void showNetworkErr();
        void startIntentActivity(int id, Article article);
        void showLoader();
        void hideLoader();
        void dataHasChanged();
    }

    interface Presenter {
        void initData();
        void fetchFailed();
        void onBindViewAtPosition(RecAdapter.MyViewHolder holder, int position);
        int getArticlesCount();
    }

    interface Model {
       void fetchData();
    }

    interface RecyclerRowView {
        void setTitle(String title);
        void setDesc(String desc);
        void showLoader();
        void hideLoader();
        void setAuthor(String author);
        void setNewsImg(String url);
        void setPublishedOn(String strDate);
        void setOnShareClickListener(Article article);
        void setOnOpenClickListener(Article article);
    }
}
