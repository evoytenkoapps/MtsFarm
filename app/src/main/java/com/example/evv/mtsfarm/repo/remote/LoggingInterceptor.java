package com.example.evv.mtsfarm.repo.remote;

import android.support.annotation.NonNull;

import com.example.evv.mtsfarm.BuildConfig;

import java.io.IOException;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


final class LoggingInterceptor implements Interceptor {
    private final Level LOG_DEBUG = Level.HEADERS;

    private final Level LOG_RELEASE = Level.NONE;

    private final Interceptor mLoggingInterceptor;

    private LoggingInterceptor() {
        mLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(getLevel());
    }

    @NonNull
    public static Interceptor create() {
        return new LoggingInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return mLoggingInterceptor.intercept(chain);
    }

    private Level getLevel() {
        if (BuildConfig.DEBUG) {
            return LOG_DEBUG;
        } else {
            return LOG_RELEASE;
        }
    }
}

