package com.example.evv.mtsfarm;

import android.app.Application;
import android.content.Context;

import com.example.evv.mtsfarm.repo.AppFarmRepository;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.repo.local.AppLocalRepo;
import com.example.evv.mtsfarm.repo.remote.AppRepoRemote;

public class App extends Application {

    private static Context context;
    private static FarmRepository mRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mRepo = AppFarmRepository.getInstance(new AppLocalRepo(), new AppRepoRemote());
    }

    public static Context getContext() {
        return context;
    }

    public static FarmRepository getRepo() {
        return mRepo;
    }
}
