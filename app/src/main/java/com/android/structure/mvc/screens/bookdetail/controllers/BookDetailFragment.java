package com.android.structure.mvc.screens.bookdetail.controllers;

import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.structure.mvc.models.book.BookManager;
import com.android.structure.mvc.models.book.Book;
import com.android.structure.mvc.screens.BaseFragment;
import com.android.structure.mvc.screens.bookdetail.views.BookDetailViewInterface;
import com.android.structure.mvc.screens.bookdetail.views.BookDetailView;

import java.util.List;


/**
 * This fragment is used to show the details of a SMS message and mark it as read
 */
public class BookDetailFragment extends BaseFragment implements
        BookDetailViewInterface.ShowDetailsViewMvcListener,
        BookManager.SmsMessagesManagerListener {

    /**
     * This constant should be used as a key in a Bundle passed to this fragment as an argument
     * at creation time. This key should correspond to the ID of the particular SMS message
     * which details will be shown in this fragment
     */
    public static final String ARG_SMS_MESSAGE_ID = "arg_sms_message_id";


    private BookDetailViewInterface mViewMVC;

    private BookManager mSmsMessagesManager;

    private long mSmsMessageId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // in general, better use dependency injection library (e.g. Dagger 2) for members' init
        mSmsMessagesManager = new BookManager(
                getActivity().getContentResolver(),
                getMainThreadPoster(),
                getBackgroundThreadPoster());

        // Get the argument of this fragment and look for the ID of the SMS message which should
        // be shown
        Bundle args = getArguments();
        if (args.containsKey(ARG_SMS_MESSAGE_ID)) {
            mSmsMessageId = args.getLong(ARG_SMS_MESSAGE_ID);
        } else {
            throw new IllegalStateException("BookDetailFragment must be started with SMS message ID argument");
        }


        // Instantiate MVC view associated with this fragment
        mViewMVC = new BookDetailView(inflater, container);
        mViewMVC.setListener(this);

        /*
        Starting with API 19 (KitKat), only applications designated as default SMS applications
        can alter SMS attributes (though they still can read SMSs), therefore on post KitKat
        versions "mark as read" button is only relevant if this app is the default SMS app.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(getActivity());
            if (!getActivity().getPackageName().equals(defaultSmsPackage)) {
                mViewMVC.markAsReadNotSupported();
            }
        }

        // Return the root view of the associated MVC view
        return mViewMVC.getRootView();
    }



    @Override
    public void onStart() {
        super.onStart();
        mSmsMessagesManager.registerListener(this);
        mSmsMessagesManager.fetchSmsMessageById(mSmsMessageId);
    }

    @Override
    public void onStop() {
        super.onStop();
        mSmsMessagesManager.unregisterListener(this);
    }

    @Override
    public void onMarkAsReadClick() {
        mSmsMessagesManager.markMessageAsRead(mSmsMessageId);
    }

    @Override
    public void onSmsMessagesFetched(List<Book> smsMessages) {
        for (Book smsMessage : smsMessages) {
            if (smsMessage.getId() == mSmsMessageId) {
                mViewMVC.bindSmsMessage(smsMessage);
                return;
            }
        }
        Toast.makeText(getActivity(), "Couldn't fetch the SMS message of interest!", Toast.LENGTH_LONG).show();
    }

}
