package com.liaoye.loltv.activity;

import android.app.Activity;
import android.os.Bundle;

import com.liaoye.loltv.R;
import com.liaoye.loltv.fragment.MainFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.ll_main,new MainFragment())
                .commit();
    }
}
