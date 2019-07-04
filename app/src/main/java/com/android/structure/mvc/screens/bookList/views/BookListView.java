package com.android.structure.mvc.screens.bookList.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.structure.mvc.R;
import com.android.structure.mvc.models.book.Book;
import com.android.structure.mvc.screens.bookList.adapters.BookListAdapter;

import java.util.List;

/**
 * This MVC view contains a list view and intercepts click events
 */
public class BookListView implements BookListViewInterface {


    private View mRootView;

    private ListView mListView;
    private BookListAdapter mBookAllListAdapter;

    private BookAllViewMvcListener mListener;

    public BookListView(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.view_home, container, false);

        /*
         Note that we are passing null instead of a Cursor - the actual Cursor with the
         results will be passed to this adapter through public "bind" method of this MVC view
          */
        mBookAllListAdapter = new BookListAdapter(inflater.getContext(), 0);
        mListView = (ListView) mRootView.findViewById(R.id.list_books);
        mListView.setAdapter(mBookAllListAdapter);


        // Register a listener for ListView's items
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onBookClicked(mBookAllListAdapter.getItem(position).getId());
                }
            }

        });
    }

    @Override
    public void setListener(BookAllViewMvcListener listener) {
        mListener = listener;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void bindBooks(List<Book> books) {
        mBookAllListAdapter.clear();
        mBookAllListAdapter.addAll(books);
        mBookAllListAdapter.notifyDataSetChanged();
    }
}
