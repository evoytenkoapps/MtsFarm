package com.example.evv.mtsfarm.repo;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.data.Temperature;
import com.example.evv.mtsfarm.data.Weight;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface FarmRepository {

    Observable<List<Cow>> getCows();

    Single<Cow> getCow();

    Observable<Void> clearDb();

    void setId(int id);

    Single<Detail> getDetail();


    Completable updateMilking(List<Milking> milkings);

    Completable updateWeight(List<Weight> weights);

    Completable updateTemperature(List<Temperature> temperatures);

}
