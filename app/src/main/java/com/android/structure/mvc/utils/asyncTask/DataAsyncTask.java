package com.android.structure.mvc.utils.asyncTask;

import android.os.AsyncTask;

/**
 * AsyncTask with Thread.sleep to simulate a network call or background work.
 */
public class DataAsyncTask<T> extends AsyncTask<Integer, Void, Void> {

    private final DataCallback<T> callback;

    private final T successObject;

    public DataAsyncTask(DataCallback<T> callback) {
        this(callback, null);
    }

    public DataAsyncTask(DataCallback<T> callback, T successObject) {
        this.callback = callback;
        this.successObject = successObject;
    }

    @Override
    protected Void doInBackground(Integer[] params) {
        if (params != null && params.length > 0) {
            // Mock : To simulate a network call
            try {
                Thread.sleep(params[0] * 1000);
            } catch (InterruptedException e) {
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void o) {
        this.callback.onDataLoaded(this.successObject);
    }
}
