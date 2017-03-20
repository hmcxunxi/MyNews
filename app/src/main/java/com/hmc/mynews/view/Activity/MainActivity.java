package com.hmc.mynews.view.Activity;

import android.os.Bundle;

import com.hmc.mynews.R;
import com.hmc.mynews.base.BaseActivity;
import com.hmc.mynews.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
