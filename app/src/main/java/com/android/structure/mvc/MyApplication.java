package com.android.structure.mvc;

import com.android.structure.mvc.common.BackgroundThreadPoster;
import com.android.structure.mvc.common.MainThreadPoster;

/**
 * Our custom {@link android.app.Application}
 */
public class MyApplication extends android.app.Application {

    private final MainThreadPoster mMainThreadPoster = new MainThreadPoster();
    private final BackgroundThreadPoster mBackgroundThreadPoster = new BackgroundThreadPoster();

    public MainThreadPoster getMainThreadPoster() {
        return mMainThreadPoster;
    }

    public BackgroundThreadPoster getBackgroundThreadPoster() {
        return mBackgroundThreadPoster;
    }
}
