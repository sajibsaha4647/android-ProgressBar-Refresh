package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private ProgressBar progressBar;
    int Progress;
    private SwipeRefreshLayout swipeRefreshLayout;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Handler handler = new  Handler();

        progressBar = findViewById(R.id.androidProgress);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(this);


        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DoWork();
            }
        });

        thread.start();

    }

    public void DoWork(){

        for(Progress = 0; Progress <= 100; Progress=Progress+10){
            try{
                Thread.sleep(1000);
                progressBar.setProgress(Progress);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (Progress == 100){ swipeRefreshLayout.setRefreshing(false);}
//                    }
//                });
            }catch (Exception e){
                e.getStackTrace();
            }
        }



    }

    @Override
    public void onRefresh() {

        if (Progress == 110){
            Progress = 0;
            progressBar.setProgress(0);
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    DoWork();
                }
            });

            thread.start();

        }
        swipeRefreshLayout.setRefreshing(false);

    }
}