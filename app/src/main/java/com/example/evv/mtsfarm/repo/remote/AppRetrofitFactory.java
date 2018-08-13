package com.example.evv.mtsfarm.repo.remote;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class AppRetrofitFactory {
    private final static String TAG = "RetrofitFactory";
    private static Retrofit mRetrofit;

    private AppRetrofitFactory() {
    }

    public static ApiGetFile getRetrofitService() {
        if (mRetrofit == null) {
            init();
        }
        return mRetrofit.create(ApiGetFile.class);
    }

    private static void init() {
        mRetrofit = new Retrofit.Builder()
                //TODO изменить на билдконфиг
                .baseUrl("http://drop5.dropmefile.com/dl/7Qy7F")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                        new OkHttpClient.Builder()
                                .connectTimeout(20, TimeUnit.SECONDS)
                                .writeTimeout(20, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                //.addInterceptor(LoggingInterceptor.create())
                                .build())
                .build();

        Log.d(TAG, "Created Retrofit: " + mRetrofit.toString());
    }
}

