package com.android.structure.mvc.screens.bookList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.structure.mvc.R;
import com.android.structure.mvc.models.Book;

class BookListItemView extends RecyclerView.ViewHolder implements BookListItemViewInterface, View.OnClickListener {

    private Context context;
    private View mRootView;

    private BookListItemViewListener mListener;

    private TextView bookNameTextView;
    private TextView bookDateTextView;
    private Book currentItem;

    public BookListItemView(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.mRootView = itemView;

        initialize();
    }

    static BookListItemView newInstance(Context context, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);

        return new BookListItemView(context, itemView);
    }

    private void initialize() {
        this.bookNameTextView = (TextView) itemView.findViewById(R.id.book_name_textview);
        this.bookDateTextView = (TextView) itemView.findViewById(R.id.book_date_textview);

        this.itemView.setOnClickListener(this);
    }

    void bindView(Book item) {
        this.currentItem = item;

        this.bookNameTextView.setText(item.title.toString());
        this.bookDateTextView.setText(item.date.toString());
    }

    @Override
    public void onClick(View v) {
        if (v == this.itemView) {
            if (this.currentItem != null) {
                    mListener.onBookClicked(this.currentItem);
            }
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

    @Override
    public void setListener(BookListItemViewInterface.BookListItemViewListener listener) {
        mListener = listener;
    }
}
