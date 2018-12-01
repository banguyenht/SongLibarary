package com.cop.huuba.data.source.local;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.cop.huuba.data.model.Song;
import com.cop.huuba.data.source.SongDataSource;

import java.util.ArrayList;
import java.util.List;

public class GetDataLocal extends AsyncTask<Void, List<Song>, List<Song>> {

    private ContentResolver mContentResolver;
    private SongDataSource.LocalCallBack mLoadSongsCallback;

    public GetDataLocal(ContentResolver contentResolver, SongDataSource.LocalCallBack loadSongsCallback) {
        mContentResolver = contentResolver;
        mLoadSongsCallback = loadSongsCallback;
    }

    @Override
    protected List<Song> doInBackground(Void... voids) {
        List<Song> songs = new ArrayList<>();
        Uri baseUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = mContentResolver.query(baseUri, null, null, null, null);

        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        } else {
            parseSong(cursor);
            while (cursor.moveToNext()) {
                songs.add(parseSong(cursor));
            }
        }
        return songs;
    }

    @Override
    protected void onPostExecute(List<Song> songs) {
        super.onPostExecute(songs);
        if (songs == null || songs.isEmpty()) {
            mLoadSongsCallback.onDataError();
        } else {
            mLoadSongsCallback.onDataLoaded(songs);
        }
    }

    private Song parseSong(Cursor cursor) {
        return new Song(cursor);
    }
}
