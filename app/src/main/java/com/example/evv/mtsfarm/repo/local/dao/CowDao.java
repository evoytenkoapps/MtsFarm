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
import io.reactivex.Single;


@Dao
public interface CowDao {
    @Query("SELECT * FROM cow")
    Flowable<List<Cow>> getCows();

    @Query("SELECT * FROM cow where id=:id")
    Single<Cow> getCow(int id);

    @Insert
    void addCows(List<Cow> cow);

    @Query("DELETE FROM cow")
    void deleteCows();
}
