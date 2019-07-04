package com.android.structure.mvc.screens.bookDetail.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.structure.mvc.R;
import com.android.structure.mvc.models.book.Book;

/**
 * An implementation of {@link BookDetailViewInterface} interface
 */
public class BookDetailView implements BookDetailViewInterface {


    private View mRootView;
    private ShowDetailsViewMvcListener mListener;
    private boolean mMarkAsReadSupported = true;

    private TextView mTxtAddress;
    private TextView mTxtDate;
    private TextView mTxtBody;
    private Button mBtnMarkAsRead;

    public BookDetailView(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.view_book_detail, container, false);

        initialize();

        mBtnMarkAsRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onMarkAsReadClick();
                }
            }
        });
    }


    private void initialize() {
        mTxtAddress = (TextView) mRootView.findViewById(R.id.txt_book_address);
        mTxtDate = (TextView) mRootView.findViewById(R.id.txt_book_date);
        mTxtBody = (TextView) mRootView.findViewById(R.id.txt_book_body);
        mBtnMarkAsRead = (Button) mRootView.findViewById(R.id.btn_mark_as_read);
    }

    @Override
    public void markAsReadNotSupported() {
        mMarkAsReadSupported = false;
    }


    @Override
    public void bindBook(Book book) {
        mTxtAddress.setText(book.getAddress());
        mTxtDate.setText(book.getDate());
        mTxtBody.setText(book.getBody());


        mRootView.setBackgroundColor(book.isUnread() ?
                mRootView.getResources().getColor(android.R.color.holo_green_light) :
                mRootView.getResources().getColor(android.R.color.white));

        mBtnMarkAsRead.setVisibility(book.isUnread() && mMarkAsReadSupported ?
                View.VISIBLE : View.GONE);

    }

    @Override
    public void setListener(ShowDetailsViewMvcListener listener) {
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

}
