package com.example.evv.mtsfarm.repo;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Storage;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface FarmRepository {

    Observable<List<Cow>> getCows();


}
