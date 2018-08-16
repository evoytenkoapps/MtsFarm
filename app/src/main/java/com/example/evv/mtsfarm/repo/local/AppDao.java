package com.example.evv.mtsfarm.repo.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.evv.mtsfarm.data.Cow;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface AppDao {

    @Query("SELECT * FROM cow")
    Flowable<List<Cow>> getCows();

    @Insert
    void insert(List<Cow> cow);

    @Update
    void update(Cow cow);

    @Delete
    void delete(Cow cow);

    @Query("DELETE FROM cow")
    void deleteCows();


}
