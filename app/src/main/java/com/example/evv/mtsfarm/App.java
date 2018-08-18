package com.example.evv.mtsfarm;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.evv.mtsfarm.repo.AppFarmRepository;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.repo.local.AppDataBase;
import com.example.evv.mtsfarm.repo.local.AppLocalRepo;
import com.example.evv.mtsfarm.repo.remote.AppRepoRemote;

public class App extends Application {

    private static Context context;
    private static FarmRepository mRepo;
    private static AppDataBase mDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mDataBase = Room.databaseBuilder(this, AppDataBase.class, "database").build();
        mRepo = AppFarmRepository.getInstance(new AppLocalRepo(), new AppRepoRemote());

    }

    public static Context getContext() {
        return context;
    }

    public static FarmRepository getRepo() {
        return mRepo;
    }

    public static AppDataBase getDatabase() {
        return mDataBase;
    }
}
