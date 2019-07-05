package com.android.structure.mvc.screens.bookList;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.android.structure.mvc.models.Book;

import java.util.ArrayList;
import java.util.List;

class BookListItemAdapter extends RecyclerView.Adapter<BookListItemView> implements BookListItemView.BookListItemViewListener{

    private Context context;
    private List<Book> itemList;

    BookListItemAdapter(Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
    }

    @Override
    public BookListItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        return BookListItemView.newInstance(parent.getContext(), parent);
    }

    @Override
    public void onBindViewHolder(BookListItemView holder, int position) {
        holder.bindView(this.itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    void setItemList(List<Book> itemList) {
        this.itemList.clear();

        if (itemList != null) {
            this.itemList.addAll(itemList);
        }

        this.notifyDataSetChanged();
    }

    @Override
    public void onBookClicked(Book book) {
        Snackbar.make(((Activity) context).getWindow().getDecorView(), String.format("%s clicked", book.title), Snackbar.LENGTH_SHORT)
                .show();
    }
}
