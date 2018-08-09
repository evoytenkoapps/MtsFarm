package com.example.evv.mtsfarm.repo.local;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.repo.FarmRepository;

import java.util.List;

import io.reactivex.Single;

public class AppLocalRepo implements FarmRepository {
    @Override
    public Single<List<Cow>> getCows() {
        return null;
    }
}
