package com.android.structure.mvc.screens.bookList;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.structure.mvc.datasources.DatasourceError;
import com.android.structure.mvc.datasources.DatasourceFactory;
import com.android.structure.mvc.R;
import com.android.structure.mvc.datasources.bookDatasource.BookDatasourceInterface;
import com.android.structure.mvc.models.Book;
import com.android.structure.mvc.utils.asyncTask.DataCallback;

import java.lang.ref.WeakReference;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements BookListView.BookListViewListener{

    private BookListView mViewMVC;

    private BookListItemAdapter adapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private BookDatasourceInterface bookDatasource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMVC = new BookListView(this, null);
        mViewMVC.setListener(this);

        setContentView(R.layout.activity_book_list);

        bookDatasource = DatasourceFactory.bookDatasource();

        this.adapter = new BookListItemAdapter(this);

        recyclerView = (RecyclerView) findViewById(R.id.book_list_recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.book_list_progressbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.adapter);

        this.loadData(
                getIntent().getStringExtra("title"),
                getIntent().getStringExtra("collection"),
                getIntent().getIntExtra("limit",10));
    }

    @Override
    public void setBookList(List<Book> bookList) {
        this.adapter.setItemList(bookList);
    }

    @Override
    public void setProgressBarVisible(boolean visible) {
        this.progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setRecyclerViewVisible(boolean visible) {
        this.recyclerView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loadData(String title, String collection, int limit) {
        this.setProgressBarVisible(true);
        this.setRecyclerViewVisible(false);

        this.bookDatasource.getBookList(new GetBookListDataCallback(this), title, collection, limit);
    }

    private void onGetBookListSucceeded(List<Book> bookList) {
        if (this != null) {
            this.setProgressBarVisible(false);
            this.setRecyclerViewVisible(true);

            this.setBookList(bookList);
        }
    }

    private void onGetBookListFailed(DatasourceError error) {
        if (this != null) {
            this.setProgressBarVisible(false);

            // TODO: Show error view
        }
    }

    private static class GetBookListDataCallback implements DataCallback<List<Book>> {

        private final WeakReference<BookListActivity> viewWeak;

        private GetBookListDataCallback(BookListActivity presenter) {
            this.viewWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(List<Book> data) {
            BookListActivity view = this.viewWeak.get();

            if (view != null) {
                view.onGetBookListSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            BookListActivity view = this.viewWeak.get();

            if (view != null) {
                view.onGetBookListFailed(error);
            }
        }
    }
}
