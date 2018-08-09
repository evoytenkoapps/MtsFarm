package com.example.evv.mtsfarm.repo;

import com.example.evv.mtsfarm.data.Cow;

import java.util.List;

import io.reactivex.Single;

public class AppFarmRepository implements FarmRepository {
    @Override
    public Single<List<Cow>> getCows() {
        return null;
    }
}
