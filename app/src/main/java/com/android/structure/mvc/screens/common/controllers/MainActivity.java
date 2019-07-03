package com.android.structure.mvc.screens.common.controllers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.android.structure.mvc.R;
import com.android.structure.mvc.screens.smsall.controllers.SmsAllFragment;
import com.android.structure.mvc.screens.common.views.MainView;


public class MainActivity extends Activity implements BaseFragment.AbstractFragmentCallback {

    MainView mViewMVC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        The MVC view associated with this activity is very simple, therefore the below lines could
        be replaced with:
        setContentView(R.layout.view_frame_layout);
        and there would be no need for FrameLayoutViewMvc class.
        However, for the sake of consistency, we should try to stick to a single coding style,
        therefore we employ full MVC approach even in this simple case!
        */

        // Instantiate MVC view associated with this activity
        mViewMVC = new MainView(this, null);

        // Set the root view of the associated MVC view as the content of this activity
        setContentView(mViewMVC.getRootView());

        // Show the default fragment if the application is not restored
        if (savedInstanceState == null) {
            replaceFragment(SmsAllFragment.class, false, null);
        }
    }




    // ---------------------------------------------------------------------------------------------
    //
    // Fragments management

    @Override
    public void replaceFragment(Class<? extends Fragment> claz, boolean addToBackStack,
                                Bundle args) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();


        Fragment newFragment;

        try {
            // Create new fragment
            newFragment = claz.newInstance();
            if (args != null) newFragment.setArguments(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

        if (addToBackStack) {
            // Add this transaction to the back stack
            ft.addToBackStack(null);
        }

        // Change to a new fragment
        ft.replace(R.id.frame_contents, newFragment, claz.getClass().getSimpleName());
        ft.commit();

    }
    // End of fragments management
    //
    // ---------------------------------------------------------------------------------------------


}
