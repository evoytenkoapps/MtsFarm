package com.example.evv.mtsfarm.repo.local;

import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.FarmRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AppLocalRepo implements FarmRepository {

    @Override
    public Observable<Storage> getData() {
        return Observable.just(new Storage());
    }

}
