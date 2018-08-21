package com.example.evv.mtsfarm.repo.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.evv.mtsfarm.data.Temperature;
import com.example.evv.mtsfarm.data.Weight;

import java.util.List;

@Dao
public interface WeightDao {
    @Insert
    void addWeight(List<Weight> weights);

    @Query("SELECT * FROM weight where id =:id")
    List<Weight> getWeight(int id);

    @Update
    void update(List<Weight> weights);

}

