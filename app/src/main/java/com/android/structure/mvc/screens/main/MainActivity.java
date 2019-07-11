package com.android.structure.mvc.screens.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.structure.mvc.R;
import com.android.structure.mvc.datasources.DatasourceError;
import com.android.structure.mvc.datasources.DatasourceFactory;
import com.android.structure.mvc.datasources.bookDatasource.BookDatasourceInterface;
import com.android.structure.mvc.models.Book;
import com.android.structure.mvc.models.Collection;
import com.android.structure.mvc.screens.bookList.BookListActivity;
import com.android.structure.mvc.utils.asyncTask.DataCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView.MainViewListener{

    MainView mViewMVC;

    private BookDatasourceInterface bookDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instantiate MVC view associated with this activity
        mViewMVC = new MainView(this, null);
        mViewMVC.setListener(this);

        bookDatasource = DatasourceFactory.bookDatasource();

        // Set the root view of the associated MVC view as the content of this activity
        setContentView(mViewMVC.getRootView());

        this.loadData();
    }

    @Override
    public void setCollectionList(List<Collection> bookList) {

    }

    @Override
    public void onButtonCaseClicked() {

        EditText txt_query = (EditText)findViewById(R.id.txt_title);
        EditText txt_limit = (EditText)findViewById(R.id.txt_limit);
        Spinner spinner_collection = (Spinner)findViewById(R.id.spinner_collection);

        String title = txt_query.getText().toString().replace(' ','+');
        int limit = txt_limit.getText().toString().equals("") ? 10 : Integer.parseInt(txt_limit.getText().toString());
        String collection = spinner_collection.getSelectedItem().toString().equals("Select collection") ? "" : spinner_collection.getSelectedItem().toString();

        Intent intent = new Intent(this.getApplicationContext(), BookListActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("collection", collection);
        intent.putExtra("limit", limit);

        this.startActivity(intent);
    }

    @Override
    public void loadData() {
        this.bookDatasource.getCollectionList(new GetCollectionDataCallback(this));
    }

    private void onGetBookListSucceeded(List<Collection> collectionList) {
        if (this != null) {

            List<String> arraySpinner = new ArrayList<>();

            arraySpinner.add("Select collection");
            for (Collection collection : collectionList) {
                arraySpinner.add(collection.setName);
            }
            ;
            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Spinner spinner = (Spinner) findViewById(R.id.spinner_collection);
            spinner.setAdapter(adapter);
        }
    }

    private void onGetBookListFailed(DatasourceError error) {
        if (this != null) {
            // TODO: Show error view
        }
    }

    private static class GetCollectionDataCallback implements DataCallback<List<Collection>> {

        private final WeakReference<MainActivity> viewWeak;

        private GetCollectionDataCallback(MainActivity presenter) {
            this.viewWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(List<Collection> data) {
            MainActivity view = this.viewWeak.get();

            if (view != null) {
                view.onGetBookListSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            MainActivity view = this.viewWeak.get();

            if (view != null) {
                view.onGetBookListFailed(error);
            }
        }
    }
}
