package com.cop.huuba.data.source.local;

import android.content.ContentResolver;
import android.content.Context;

import com.cop.huuba.data.source.SongDataSource;

public class SongLocalDataSoure {
    private static SongLocalDataSoure sInstance;

    public static SongLocalDataSoure getInstance() {
        if (sInstance == null) {
            sInstance = new SongLocalDataSoure();
        }
        return sInstance;
    }

    public void loadDataLocal(ContentResolver contentResolver , SongDataSource.LocalCallBack callBack) {
        new GetDataLocal(contentResolver , callBack).execute();
    }
}
