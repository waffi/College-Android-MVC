package com.android.structure.mvc.screens.bookList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.structure.mvc.R;

/**
 * Very simple MVC view containing just single FrameLayout
 */
public class BookListView implements BookListViewInterface {

    private View mRootView;
    private BookListViewListener mListener;

    private BookListItemAdapter adapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public BookListView(Context context, ViewGroup container) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.activity_book_list, container);

        initialize();

        adapter = new BookListItemAdapter(LayoutInflater.from(context).getContext());
        recyclerView.setAdapter(adapter);
    }

    private void initialize() {

        progressBar = (ProgressBar) mRootView.findViewById(R.id.book_list_progressbar);
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.book_list_recyclerview);
    }


    @Override
    public void setListener(BookListViewInterface.BookListViewListener listener) {
        mListener = listener;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        // This MVC view has no state that could be retrieved
        return null;
    }
}
