package com.android.structure.mvc.screens.bookList.views;

import com.android.structure.mvc.models.book.Book;
import com.android.structure.mvc.screens.base.BaseViewInterface;

import java.util.List;

/**
 * This MVC view corresponds to a screen where a list containing all Books is shown
 */
public interface BookListViewInterface extends BaseViewInterface {


    interface BookAllViewMvcListener {
        /**
         * This callback will be invoked when the user clicks on one of the shown Books
         * @param id clicked book's ID
         */
        void onBookClicked(long id);
    }

    /**
     * Bind Books data which should be shown by this MVC view
     * @param books list of {@link Book} objects that need to be shown
     */
    void bindBooks(List<Book> books);

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(BookAllViewMvcListener listener);

}
