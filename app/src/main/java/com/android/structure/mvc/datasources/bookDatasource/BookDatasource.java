package com.android.structure.mvc.datasources.bookDatasource;

import com.android.structure.mvc.models.Search;
import com.android.structure.mvc.utils.apiHelper.ApiClientInstance;
import com.android.structure.mvc.utils.apiHelper.DataService;
import com.android.structure.mvc.utils.asyncTask.DataCallback;
import com.android.structure.mvc.models.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDatasource implements BookDatasourceInterface {

    @Override
    public void getBookList(final DataCallback<List<Book>> callback, String title, int limit) {
        DataService service = ApiClientInstance.getRetrofitInstance().create(DataService.class);

        Call<Search> call = service.getItems(title, limit);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                callback.onDataLoaded(response.body().items.dc);
                System.out.println(response.body().items.dc.size());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
