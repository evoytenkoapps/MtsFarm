package com.example.evv.mtsfarm.repo.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Milking;

@Database(entities = {Cow.class, Milking.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract AppDao appDao();

}
