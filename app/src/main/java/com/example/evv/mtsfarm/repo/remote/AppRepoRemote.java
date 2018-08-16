package com.example.evv.mtsfarm.repo.remote;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.repo.local.AppDataBase;
import com.example.evv.mtsfarm.repo.remote.retrofit.AppRetrofit;
import com.example.evv.mtsfarm.utils.ExcelParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

public class AppRepoRemote {
    private final String TAG = this.getClass().getSimpleName();
    private final String FILE_NAME = "Data.xls";
    private final String DOWNLOAD_URL = "https://www.mango-office.ru/upload/support/data/Data2003.xls";

    public Observable<Storage> getData() {
        return AppRetrofit.getRetrofitService().getFile(DOWNLOAD_URL)
                .flatMap(this::saveToDiskRx)
                .toObservable()
                .flatMap(this::parseExcel);
    }

    public Observable clearDb() {
        return null;
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

    private Observable<Storage> parseExcel(File file) {
        return Observable.create(emitter -> {
            try {
                ExcelParser excelParser = new ExcelParser(file);
                Storage data = excelParser.parseData();
                if (!emitter.isDisposed()) {
                    emitter.onNext(data);
                    emitter.onComplete();
                }
            } catch (IOException e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }
}
