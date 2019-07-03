package com.android.structure.mvc.screens.booklist.views;

import com.android.structure.mvc.models.book.Book;
import com.android.structure.mvc.screens.BaseViewInterface;

/**
 * This interface corresponds to a single SMS preview element (thumbnail) which is intended to
 * be used in a list of similar elements
 */
public interface BookListThumbnailViewInterface extends BaseViewInterface {

    /**
     * Show thumbnail of a particular SMS message
     * @param smsMessage the message to show
     */
    void bindSmsMessage(Book smsMessage);
}
