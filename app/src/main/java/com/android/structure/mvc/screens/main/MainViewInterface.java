package com.android.structure.mvc.screens.main;

import com.android.structure.mvc.models.Collection;
import com.android.structure.mvc.screens.base.BaseViewInterface;

import java.util.List;

/**
 * This interface corresponds to "details" screen of the app, where details of a single SMS
 * book should be displayed
 */
public interface MainViewInterface extends BaseViewInterface {


    interface MainViewListener {
        /**
         * This callback will be invoked when "mark as read" button is being clicked
         */

        void setCollectionList(List<Collection> bookList);

        void onButtonCaseClicked();

        void loadData();
    }

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(MainViewListener listener);

}
