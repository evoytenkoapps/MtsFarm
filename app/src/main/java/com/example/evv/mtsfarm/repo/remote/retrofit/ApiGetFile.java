package com.example.evv.mtsfarm.repo.remote.retrofit;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiGetFile {

    @Streaming
    @GET()
    Single<Response<ResponseBody>> getFile(@Url String url);
    
}
