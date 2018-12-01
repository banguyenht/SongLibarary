package com.cop.huuba.data.source.remote;

import com.cop.huuba.data.model.Genre;
import com.cop.huuba.data.source.SongDataSource;

public class SongRemoteDataSource {

    private static SongRemoteDataSource sInstance;

    public static SongRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongRemoteDataSource();
        }
        return sInstance;
    }

    public void loadData(Genre genre, SongDataSource.CallBack callBack) {
        new GetDataApi(genre, callBack).execute(genre.getUrl());
    }

    public void loadSong(Genre genre, SongDataSource.CallBack callBack){
        new GetSongAsyncTask(genre, callBack).execute(genre.getUrl());
    }
}