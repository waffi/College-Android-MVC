package com.android.structure.mvc.screens.bookList;

import com.android.structure.mvc.models.Book;
import com.android.structure.mvc.screens.base.BaseViewInterface;

/**
 * This interface corresponds to "details" screen of the app, where details of a single SMS
 * book should be displayed
 */
public interface BookListItemViewInterface extends BaseViewInterface {


    interface BookListItemViewListener {
        /**
         * This callback will be invoked when "mark as read" button is being clicked
         */

        void onBookClicked(Book book);
    }

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(BookListItemViewListener listener);

}
