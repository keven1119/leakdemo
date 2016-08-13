package com.keven.leak.leakdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executors;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.internal.util.RxThreadFactory;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class RxActivity extends AppCompatActivity implements View.OnClickListener {

    CompositeSubscription compositeSubscription = new CompositeSubscription();
    TextView textView ;
    Button buttonRx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxtest);
        textView = (TextView) findViewById(R.id.text);
        buttonRx = (Button) findViewById(R.id.btn_start_rxjava);

        buttonRx.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!compositeSubscription.isUnsubscribed()){
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onClick(View v) {
        Executors.newFixedThreadPool(200,)

        compositeSubscription.add(new RxTest().testRx()
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("keven1119","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        textView.setText(s);
                    }
                }));
    }
}
