package com.android.structure.mvc.datasources;

import com.android.structure.mvc.datasources.bookDatasource.BookDatasource;
import com.android.structure.mvc.datasources.bookDatasource.BookDatasourceInterface;

public class DatasourceFactory {

    private DatasourceFactory() {
    }

    public static BookDatasourceInterface bookDatasource() {
        return new BookDatasource();
    }
}
