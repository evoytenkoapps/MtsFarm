package com.example.evv.mtsfarm.repo.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.evv.mtsfarm.data.Milking;

import java.util.List;

@Dao
public interface MilkingDao {

    @Insert
    void addMilking(List<Milking> milking);
}
