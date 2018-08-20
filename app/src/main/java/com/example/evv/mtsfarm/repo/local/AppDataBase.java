package com.example.evv.mtsfarm.repo.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.data.Temperature;
import com.example.evv.mtsfarm.data.Weight;
import com.example.evv.mtsfarm.repo.local.dao.CowDao;
import com.example.evv.mtsfarm.repo.local.dao.MilkingDao;
import com.example.evv.mtsfarm.repo.local.dao.TemperatureDao;
import com.example.evv.mtsfarm.repo.local.dao.WeightDao;

@Database(entities = {Cow.class, Milking.class, Weight.class, Temperature.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract CowDao cowDao();

    public abstract MilkingDao milkingDao();

    public abstract WeightDao weightDao();

    public abstract TemperatureDao temperatureDao();
}
