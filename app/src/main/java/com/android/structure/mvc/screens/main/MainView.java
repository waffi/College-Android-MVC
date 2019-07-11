package com.android.structure.mvc.screens.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.android.structure.mvc.R;

/**
 * Very simple MVC view containing just single FrameLayout
 */
public class MainView implements MainViewInterface {

    private View mRootView;
    private MainViewListener mListener;

    private Button mBtmLimit;

    public MainView(Context context, ViewGroup container) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.activity_main, container);

        initialize();

        mBtmLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onButtonCaseClicked();
                }
            }
        });
    }

    private void initialize() {
        mBtmLimit = (Button) mRootView.findViewById(R.id.btn_search);
    }


    @Override
    public void setListener(MainViewListener listener) {
        mListener = listener;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        // This MVC view has no state that could be retrieved
        return null;
    }
}
