package com.example.administrator.savepassword.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity{

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面切换需要动画
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        init();
    }

    protected void init() {}

    public abstract int getLayoutResID();

    protected void showToast(final String text){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
