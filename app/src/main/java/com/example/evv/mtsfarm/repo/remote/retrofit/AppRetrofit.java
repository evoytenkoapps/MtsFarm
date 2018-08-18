package com.example.evv.mtsfarm.repo.remote.retrofit;

import android.util.Log;

import com.example.evv.mtsfarm.App;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class AppRetrofit {
    private final static String TAG = "RetrofitFactory";
    private static Retrofit mRetrofit;
    //private static CookieManager cookieManager;

    private AppRetrofit() {
    }

    public static ApiGetFile getRetrofitService() {
        if (mRetrofit == null) {
            init();
        }
        return mRetrofit.create(ApiGetFile.class);
    }

    private static void init() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);


        mRetrofit = new Retrofit.Builder()
                //TODO изменить на билдконфиг
                .baseUrl("https://dropmefiles.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                        new OkHttpClient.Builder()
                                .cookieJar(new JavaNetCookieJar(cookieManager))
                                .connectTimeout(20, TimeUnit.SECONDS)
                                .writeTimeout(20, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .addInterceptor(LoggingInterceptor.create())
                                .addInterceptor(new ConnectivityInterceptor(App.getContext()))
                                .build())
                .build();

        Log.d(TAG, "Created Retrofit: " + mRetrofit.toString());
    }
}

