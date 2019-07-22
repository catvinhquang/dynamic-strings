package com.quangcv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by QuangCV on 22-Jul-2019
 **/

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new CustomContextWrapper(newBase));
    }

}