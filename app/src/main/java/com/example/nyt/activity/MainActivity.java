package com.example.nyt.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nyt.R;
import com.example.nyt.contract.MainContract;
import com.example.nyt.model.Article;
import com.example.nyt.presenter.MainActivityPresenterImp;
import com.example.nyt.view.RecAdapter;
import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private RecyclerView recyclerView;
    AVLoadingIndicatorView avLoadingIndicatorView;
    private MainActivityPresenterImp presenterImp;
    private RecyclerView.LayoutManager layoutManager;
    private RecAdapter recAdapter;
    private String TAG = MainActivity.class.getSimpleName();
    MenuInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Top technical stories");
        avLoadingIndicatorView = findViewById(R.id.main_loader);

        showLoader();
        presenterImp = new MainActivityPresenterImp(this);
        presenterImp.initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh){
            showLoader();
            presenterImp.initData();
        }
        return true;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.mainRecyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recAdapter = new RecAdapter(presenterImp);
        recyclerView.setAdapter(recAdapter);
        recAdapter.notifyDataSetChanged();
    }


    @Override
    public void showNetworkErr() {
        Toast.makeText(this,"Unable to fetch news feed, Please try later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoader() {
        avLoadingIndicatorView.show();
    }

    @Override
    public void hideLoader() {
        avLoadingIndicatorView.hide();
    }

    @Override
    public void dataHasChanged() {
        initView();
        hideLoader();
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
        }
    }

    @Override
    public void startIntentActivity(int id, Article article){
        Intent intent;

        try {
        if (id == 1){

                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, article.getTitle());
                String body = article.getTitle()+"\n\n"
                        +article.getnAbstract()
                        +"\n\n"+article.getUrl()
                        +"\n\n-via NYT news app.";
                intent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intent,"Share with"));

        }else if (id == 2) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(article.getUrl()));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Oops! unable to perfom action.", Toast.LENGTH_SHORT).show();
        }

        }catch (Exception e){
            Toast.makeText(this, "Oops! unable to perfom action.", Toast.LENGTH_SHORT).show();
        }
    }

}
