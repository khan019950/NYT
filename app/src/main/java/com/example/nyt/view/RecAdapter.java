package com.example.nyt.view;



import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.nyt.R;
import com.example.nyt.contract.MainContract;
import com.example.nyt.model.Article;
import com.example.nyt.presenter.MainActivityPresenterImp;
import com.wang.avi.AVLoadingIndicatorView;


// Adapter class for our recyclerview

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {

    private MainActivityPresenterImp mPresenterImp;

    public RecAdapter(MainActivityPresenterImp presenterImp) {
        this.mPresenterImp = presenterImp;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        mPresenterImp.onBindViewAtPosition(holder, position);
    }


    @Override
    public int getItemCount() {
        return mPresenterImp.getArticlesCount();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements MainContract.RecyclerRowView {

        TextView publishedOn, title, desc, author;
        ImageView shareVia, openWith, articleImg;
        AVLoadingIndicatorView avLoadingIndicatorView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            avLoadingIndicatorView = itemView.findViewById(R.id.ac_loader); //Loader for feed img
            title = itemView.findViewById(R.id.ac_title); // news title
            publishedOn = itemView.findViewById(R.id.ac_pub_on); // Date of publish
            desc = itemView.findViewById(R.id.ac_desc); // Abstract of news
            author = itemView.findViewById(R.id.ac_author); // news Author
            shareVia = itemView.findViewById(R.id.ac_share); // share news via available apps
            openWith = itemView.findViewById(R.id.ac_open); // open / read full article on chrome / safari app
            articleImg = itemView.findViewById(R.id.ac_img); // img of article

        }

        @Override
        public void setTitle(String title) {
            this.title.setText(title);
        }

        @Override
        public void setDesc(String desc) {
            this.desc.setText(desc);
        }

        @Override
        public void showLoader() {
            this.avLoadingIndicatorView.show();
        }

        @Override
        public void hideLoader() {
            this.avLoadingIndicatorView.hide();
        }

        @Override
        public void setAuthor(String author) {
            this.author.setText(author);
        }

        @Override
        public void setNewsImg(String url) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.color.cardview_shadow_start_color);
            requestOptions.error(R.color.colorPrimaryDark);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            requestOptions.centerCrop();

            Glide.with(itemView.getContext()).load(url).apply(requestOptions).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    hideLoader();
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    hideLoader();
                    return false;
                }
            }).transition(DrawableTransitionOptions.withCrossFade()).into(this.articleImg);
        }

        @Override
        public void setPublishedOn(String strDate) {
            this.publishedOn.setText(strDate);
        }

        @Override
        public void setOnShareClickListener(final Article article) {
            shareVia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenterImp.intentActivity(1, article);
                }
            });
        }

        @Override
        public void setOnOpenClickListener(final Article article) {
            openWith.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenterImp.intentActivity(2, article);
                }
            });
        }

    }

}
