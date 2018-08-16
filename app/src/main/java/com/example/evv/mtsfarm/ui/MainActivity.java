package com.example.evv.mtsfarm.ui;

import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private ContractMain.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new MainFragment());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new AppPresenter();
        mPresenter.init();
    }
}
