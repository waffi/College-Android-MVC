package com.android.structure.mvc.utils.asyncTask;

import com.android.structure.mvc.datasources.DatasourceError;

public interface DataCallback<T> {

    void onDataLoaded(T data);

    void onDataError(DatasourceError error);
}
