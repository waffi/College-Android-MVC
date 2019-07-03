package com.android.structure.mvc.screens.bookList.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.structure.mvc.models.book.BookManager;
import com.android.structure.mvc.models.book.Book;
import com.android.structure.mvc.screens.base.BaseFragment;
import com.android.structure.mvc.screens.bookDetail.controllers.BookDetailFragment;
import com.android.structure.mvc.screens.bookList.views.BookListViewInterface;
import com.android.structure.mvc.screens.bookList.views.BookListView;

import java.util.List;

/**
 * A Fragment containing a list of SMS messages
 */
public class BookListFragment extends BaseFragment implements
        BookListViewInterface.SmsAllViewMvcListener,
        BookManager.SmsMessagesManagerListener {

    BookListViewInterface mViewMVC;

    BookManager mSmsMessagesManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // in general, better use dependency injection library (e.g. Dagger 2) for members' init
        mSmsMessagesManager = new BookManager(
                getActivity().getContentResolver(),
                getMainThreadPoster(),
                getBackgroundThreadPoster());

        // Instantiate MVC view and set the fragment as listener
        mViewMVC = new BookListView(inflater, container);
        mViewMVC.setListener(this);

        // Return the root view of the associated MVC view
        return mViewMVC.getRootView();
    }


    @Override
    public void onStart() {
        super.onStart();
        mSmsMessagesManager.registerListener(this);
        mSmsMessagesManager.fetchAllSmsMessages();
    }

    @Override
    public void onStop() {
        super.onStop();
        mSmsMessagesManager.unregisterListener(this);
    }

    @Override
    public void onSmsMessageClicked(long id) {
        // Create a bundle that will pass the ID of the clicked SMS message to the new fragment
        Bundle args = new Bundle(1);
        args.putLong(BookDetailFragment.ARG_SMS_MESSAGE_ID, id);

        // Replace this fragment with a new one and pass args to it
        replaceFragment(BookDetailFragment.class, true, args);
    }

    @Override
    public void onSmsMessagesFetched(List<Book> smsMessages) {
        mViewMVC.bindSmsMessages(smsMessages);
    }
}
