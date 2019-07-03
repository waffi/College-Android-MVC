package com.android.structure.mvc.screens.bookList.views;

import com.android.structure.mvc.models.book.Book;
import com.android.structure.mvc.screens.base.BaseViewInterface;

import java.util.List;

/**
 * This MVC view corresponds to a screen where a list containing all SMS messages is shown
 */
public interface BookListViewInterface extends BaseViewInterface {


    interface SmsAllViewMvcListener {
        /**
         * This callback will be invoked when the user clicks on one of the shown SMS messages
         * @param id clicked message's ID
         */
        void onSmsMessageClicked(long id);
    }

    /**
     * Bind SMS messages data which should be shown by this MVC view
     * @param smsMessages list of {@link Book} objects that need to be shown
     */
    void bindSmsMessages(List<Book> smsMessages);

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(SmsAllViewMvcListener listener);

}
