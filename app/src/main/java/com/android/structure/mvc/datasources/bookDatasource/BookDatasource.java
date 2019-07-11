package com.android.structure.mvc.datasources.bookDatasource;

import com.android.structure.mvc.models.Collection;
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
    public void getBookList(final DataCallback<List<Book>> callback, String title, String collection, int limit) {
        DataService service = ApiClientInstance.getRetrofitInstance().create(DataService.class);

        Call<Search> call = null;

        System.out.println(title);
        System.out.println(collection);
        System.out.println(limit);

        if (collection.equals("")){
            if (title.equals("")){
                call = service.getItems1(limit);
            }
            else{
                call = service.getItems2(title, limit);
            }
        }
        else{
            if (title.equals("")){
                call = service.getItems1(collection, limit);
            }
            else{
                call = service.getItems2(title, collection, limit);
            }
        }

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

    @Override
    public void getCollectionList(final DataCallback<List<Collection>> callback) {
        DataService service = ApiClientInstance.getRetrofitInstance().create(DataService.class);

        Call<List<Collection>> call = service.getCollections();
        call.enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                callback.onDataLoaded(response.body());
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
