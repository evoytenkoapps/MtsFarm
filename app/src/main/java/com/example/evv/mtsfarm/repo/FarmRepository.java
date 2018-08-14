package com.example.evv.mtsfarm.repo;

import com.example.evv.mtsfarm.data.Storage;

import io.reactivex.Single;

public interface FarmRepository {

    Single<Storage> getData();

}
