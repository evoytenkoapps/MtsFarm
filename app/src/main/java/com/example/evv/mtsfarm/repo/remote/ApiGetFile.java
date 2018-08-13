package com.example.evv.mtsfarm.repo.remote;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface ApiGetFile {
    @Streaming
    @GET("{path}")
    Single<Response<ResponseBody>> getFile(@Path("path") String path);
}