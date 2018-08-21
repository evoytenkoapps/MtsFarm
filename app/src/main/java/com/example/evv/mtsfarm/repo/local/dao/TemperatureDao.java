package com.example.evv.mtsfarm.repo.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.data.Temperature;

import java.util.List;

@Dao
public interface TemperatureDao {
    @Insert
    void addTemperature(List<Temperature> temperatures);


    @Query("SELECT * FROM temperature where id =:id")
    List<Temperature> getTemperature(int id);

    @Query("UPDATE temperature SET date=:date, temperature=:temperature WHERE `key`=:key")
    void updateTemperature(int key, String date, double temperature);
}
