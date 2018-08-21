package com.example.evv.mtsfarm.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.ui.detail.FragmentDetail;
import com.example.evv.mtsfarm.ui.main.FragmentMain;

public class MainActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private final String TAG = this.getClass().getSimpleName();
    private final String MAIN_FRAGMENT = "MAIN_FRAGMENT";
    private final String DETAIL_FRAGMENT = "DETAIL_FRAGMENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFragmentManager = getSupportFragmentManager();
        showMainFragment();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainFragment();
            }
        });
    }

    public void showMainFragment() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new FragmentMain(), MAIN_FRAGMENT).commit();
    }

    public void showDetailFragment() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new FragmentDetail(), DETAIL_FRAGMENT).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "press back");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.findFragmentByTag(DETAIL_FRAGMENT) != null) {
            showMainFragment();
        } else {
            super.onBackPressed();
        }
    }
}
