package com.keven.leak.leakdemo;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class RxTest {
    public Observable<String> testRx(){
        return  Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = longTimeTask();
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                }).start();

            }
        });
    }

    private String longTimeTask(){
        String result = "";
        int i = 0;

        try {
            while (i<50){
                i++;
                Thread.sleep(1000);
                Log.d("keven1119","longTimeTask() called with:" + i);
            }
            result = "执行完毕";
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return result;
    }
}
