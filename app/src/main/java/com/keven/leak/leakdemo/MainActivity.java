package com.keven.leak.leakdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class MainActivity extends AppCompatActivity {

    Button mButtonToLeak;
    Button mButtonToRx;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonToLeak = (Button) findViewById(R.id.button_to_leak);
        mButtonToRx = (Button) findViewById(R.id.button_to_Rx);

        mButtonToLeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeakActivity.class);
                startActivity(intent);
            }
        });

        mButtonToRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxActivity.class);
                startActivity(intent);
            }
        });
    }
}
