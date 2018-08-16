package com.example.evv.mtsfarm.repo.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.repo.local.dao.CowDao;
import com.example.evv.mtsfarm.repo.local.dao.MilkingDao;

@Database(entities = {Cow.class, Milking.class}, version = 2, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract CowDao cowDao();

    public abstract MilkingDao milkingDao();
}
