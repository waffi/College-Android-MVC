package com.android.structure.mvc.screens.bookList;

import android.support.design.widget.Snackbar;

import com.android.structure.mvc.models.Book;
import com.android.structure.mvc.screens.base.BaseViewInterface;

import java.util.List;

/**
 * This interface corresponds to "details" screen of the app, where details of a single SMS
 * book should be displayed
 */
public interface BookListViewInterface extends BaseViewInterface {


    interface BookListViewListener {
        /**
         * This callback will be invoked when "mark as read" button is being clicked
         */

        void setBookList(List<Book> bookList);

        void setProgressBarVisible(boolean visible);

        void setRecyclerViewVisible(boolean visible);

        void loadData(int limit);
    }

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(BookListViewListener listener);

}
