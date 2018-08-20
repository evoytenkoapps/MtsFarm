package com.example.evv.mtsfarm.repo.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.evv.mtsfarm.data.Temperature;

import java.util.List;

@Dao
public interface TemperatureDao {
    @Insert
    void addTemperature(List<Temperature> temperatures);
}
