package com.example.evv.mtsfarm.repo.remote;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiGetFile {

    @Streaming
    @GET()
    Single<Response<ResponseBody>> getFile(@Url String url);
}
