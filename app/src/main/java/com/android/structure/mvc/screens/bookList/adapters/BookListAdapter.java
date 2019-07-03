package com.android.structure.mvc.screens.bookList.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.structure.mvc.models.book.Book;
import com.android.structure.mvc.screens.bookList.views.BookListThumbnailViewInterface;
import com.android.structure.mvc.screens.bookList.views.BookListThumbnailView;

/**
 * This adapter handles population of a list with instances of {@link BookListThumbnailViewInterface}
 */
public class BookListAdapter extends ArrayAdapter<Book> {

    public BookListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookListThumbnailViewInterface smsThumbnailViewMvc;
        if (convertView == null) {
            smsThumbnailViewMvc =  new BookListThumbnailView(getContext(), parent);
            /*
             Since this kind of adapters store just references to Android Views, we need to "attach"
             the entire MVC view as a tag to its root view in order to be able to retrieve it later.
             Usage of MVC view in such a way completely eliminates a need for ViewHolder.
             This is just a workaround though, the better option would be to create our own adapter
             for MVC views...
            */
            smsThumbnailViewMvc.getRootView().setTag(smsThumbnailViewMvc);
        } else {
            smsThumbnailViewMvc = ((BookListThumbnailViewInterface) convertView.getTag());
        }

        smsThumbnailViewMvc.bindSmsMessage(getItem(position));
        return smsThumbnailViewMvc.getRootView();
    }

}
