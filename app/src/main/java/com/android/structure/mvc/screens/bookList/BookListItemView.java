package com.android.structure.mvc.screens.bookList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.structure.mvc.R;
import com.android.structure.mvc.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

class BookListItemView extends RecyclerView.ViewHolder implements BookListItemViewInterface, View.OnClickListener {

    private Context context;
    private View mRootView;

    private BookListItemViewListener mListener;

    private TextView bookNameTextView;
    private TextView bookDescriptionTextView;
    private ImageView bookIdentifierImageView;
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
        this.bookDescriptionTextView = (TextView) itemView.findViewById(R.id.book_desc_textview);
        this.bookIdentifierImageView = (ImageView) itemView.findViewById(R.id.book_identifier_img);

        this.itemView.setOnClickListener(this);
    }

    void bindView(Book item) {
        this.currentItem = item;

        try {
            this.bookNameTextView.setText(item.title.toString());
            this.bookDescriptionTextView.setText(item.description.toString());
            Picasso.with(context).load(((List<String>) item.identifier).get(0)).into(this.bookIdentifierImageView);
        }
        catch (Exception e){

        }
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
