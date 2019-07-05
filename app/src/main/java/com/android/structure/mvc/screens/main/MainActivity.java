package com.android.structure.mvc.screens.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.android.structure.mvc.R;
import com.android.structure.mvc.screens.bookList.BookListActivity;

public class MainActivity extends Activity implements MainView.MainViewListener{

    MainView mViewMVC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instantiate MVC view associated with this activity
        mViewMVC = new MainView(this, null);
        mViewMVC.setListener(this);

        // Set the root view of the associated MVC view as the content of this activity
        setContentView(mViewMVC.getRootView());

    }

    @Override
    public void onButtonCaseClicked() {

        EditText txt_limit = (EditText)findViewById(R.id.txt_limit);

        Intent intent = new Intent(this.getApplicationContext(), BookListActivity.class);
        intent.putExtra("limit", Integer.parseInt(txt_limit.getText().toString()));

        this.startActivity(intent);
    }
}
