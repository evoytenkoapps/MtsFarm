package com.example.evv.mtsfarm.repo;

import com.example.evv.mtsfarm.data.Cow;

import java.util.List;

import io.reactivex.Single;

public interface FarmRepository {

    Single<List<Cow>> getCows();
    
}
