package com.android.structure.mvc.screens.main.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.structure.mvc.R;
import com.android.structure.mvc.screens.base.BaseViewInterface;

/**
 * Very simple MVC view containing just single FrameLayout
 */
public class MainView implements BaseViewInterface {

    private View mRootView;

    public MainView(Context context, ViewGroup container) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_frame_layout, container);
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
