package com.android.structure.mvc.screens.smsall.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.structure.mvc.models.sms.SmsMessagesManager;
import com.android.structure.mvc.models.sms.SmsMessage;
import com.android.structure.mvc.screens.common.controllers.BaseFragment;
import com.android.structure.mvc.screens.smsdetails.controllers.SmsDetailsFragment;
import com.android.structure.mvc.screens.smsall.views.SmsAllViewInterface;
import com.android.structure.mvc.screens.smsall.views.SmsAllView;

import java.util.List;

/**
 * A Fragment containing a list of SMS messages
 */
public class SmsAllFragment extends BaseFragment implements
        SmsAllViewInterface.SmsAllViewMvcListener,
        SmsMessagesManager.SmsMessagesManagerListener {

    SmsAllViewInterface mViewMVC;

    SmsMessagesManager mSmsMessagesManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // in general, better use dependency injection library (e.g. Dagger 2) for members' init
        mSmsMessagesManager = new SmsMessagesManager(
                getActivity().getContentResolver(),
                getMainThreadPoster(),
                getBackgroundThreadPoster());

        // Instantiate MVC view and set the fragment as listener
        mViewMVC = new SmsAllView(inflater, container);
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
        args.putLong(SmsDetailsFragment.ARG_SMS_MESSAGE_ID, id);

        // Replace this fragment with a new one and pass args to it
        replaceFragment(SmsDetailsFragment.class, true, args);
    }

    @Override
    public void onSmsMessagesFetched(List<SmsMessage> smsMessages) {
        mViewMVC.bindSmsMessages(smsMessages);
    }
}
