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

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

public class AppRemoteRepo implements FarmRepository {
    private final String TAG = this.getClass().getSimpleName();
    private final String FILE_NAME = "Data.xlsx";
    private final String PATH = "7Qy7F";

    @Override

    public Single<List<Cow>> getCows() {
        return AppRetrofit.getRetrofitService().getFile(PATH)
                .subscribeOn(Schedulers.io())
                .flatMap(response -> saveToDiskRx(response))
                .flatMap(file -> parseFile(file));

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

    private Single<List<Cow>> parseFile(final File file) {
        return Single.create(emitter -> {
//            ExcelParser excelParser = new ExcelParser();
//            excelParser.setInputFile(file.getAbsolutePath());
            emitter.onSuccess(new ArrayList<Cow>());
        });
    }


//    private String getFilePath() {
//
//        if (!externalStorageAvailable()) {
//            getView().showToast(R.string.call_record_storage_unavailable);
//        }
//
//        String path = ((AppCallRecordFragment) getView()).getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath();
//        Log.d(TAG, "Storage available: " + externalStorageAvailable());
//        Log.d(TAG, "path: " + path);
//        String fileName = mStorage.getRecordInfo().get(0) + " " +
//                mStorage.getRecordInfo().get(1) + " " +
//                mStorage.getRecordInfo().get(2) +
//                ".mp3";
//        return path + "/" + fileName;
//    }


}
