package com.example.evv.mtsfarm.repo.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Milking;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface CowDao {
    @Query("SELECT * FROM cow")
    Flowable<List<Cow>> getCows();

    @Insert
    void addCows(List<Cow> cow);

    @Update
    void update(Cow cow);

    @Delete
    void delete(Cow cow);

    @Query("DELETE FROM cow")
    void deleteCows();
}