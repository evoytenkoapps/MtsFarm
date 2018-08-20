package com.example.evv.mtsfarm.repo.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.evv.mtsfarm.data.Weight;

import java.util.List;

@Dao
public interface WeightDao {
    @Insert
    void addWeight(List<Weight> weights);

}

