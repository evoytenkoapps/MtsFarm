package com.example.evv.mtsfarm.repo.remote;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.utils.ExcelParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

public class AppRepoRemote implements FarmRepository {
    private final String TAG = this.getClass().getSimpleName();
    private final String FILE_NAME = "Data.xls";
    private final String DOWNLOAD_URL = "https://www.mango-office.ru/upload/support/data/Data2003.xls";

    @Override

    public Single<List<Cow>> getCows() {

        return AppRetrofit.getRetrofitService().getFile(DOWNLOAD_URL)
                .flatMap(this::saveToDiskRx)
                .flatMap(this::parseFile);
    }


    private Single<File> saveToDiskRx(final Response<ResponseBody> response) {
        return Single.create(emitter -> {
            try {
                File file = new File(App.getContext().getFilesDir(), FILE_NAME);
                BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
                bufferedSink.writeAll(response.body().source());
                bufferedSink.close();
                if (!emitter.isDisposed()) {
                    emitter.onSuccess(file);

                }

            } catch (IOException e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }

    private Single<List<Cow>> parseFile(File file) {
        return Single.create(emitter -> {
            ExcelParser excelParser = new ExcelParser();
            excelParser.read(file);
        });
    }
}
