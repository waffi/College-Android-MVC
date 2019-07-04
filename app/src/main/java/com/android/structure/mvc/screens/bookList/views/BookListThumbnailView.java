package com.android.structure.mvc.screens.bookList.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.structure.mvc.R;
import com.android.structure.mvc.commons.Utils;
import com.android.structure.mvc.models.book.Book;

/**
 * This MVC view represents a single element in ListView.
 */
public class BookListThumbnailView implements BookListThumbnailViewInterface {

    private final Context context;
    View mRootView;

    private TextView mTxtAddress;
    private TextView mTxtDate;


    public BookListThumbnailView(Context context, ViewGroup container) {
        this.context = context;
        mRootView = LayoutInflater.from(context)
                .inflate(R.layout.view_book_thumbnail, container, false);

        initialize();
    }

    /**
     * This method initializes member fields of this object
     */
    private void initialize() {
        mTxtAddress = (TextView) mRootView.findViewById(R.id.txt_book_address);
        mTxtDate = (TextView) mRootView.findViewById(R.id.txt_book_date);
    }

    @Override
    public void bindBook(Book book) {
        mTxtAddress.setText(book.getAddress());
        mTxtDate.setText(Utils.convertToHumanReadableDate(book.getDate()));

        // Change the background depending on whether the book has already been read
        if (book.isUnread()) {
            mRootView.setBackgroundColor(
                    context.getResources().getColor(android.R.color.holo_green_light));
        } else {
            mRootView.setBackgroundColor(
                    context.getResources().getColor(android.R.color.white));
        }
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
