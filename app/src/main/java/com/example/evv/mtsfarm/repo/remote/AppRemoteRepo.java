package com.example.evv.mtsfarm.repo.remote;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.repo.FarmRepository;

import java.util.List;

import io.reactivex.Single;

public class AppRemoteRepo implements FarmRepository {
    @Override
    public Single<List<Cow>> getCows() {
        return null;
    }
}
