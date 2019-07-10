package com.android.structure.mvc.screens.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.structure.mvc.R;
import com.android.structure.mvc.screens.bookList.BookListActivity;

public class MainActivity extends AppCompatActivity implements MainView.MainViewListener{

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

        EditText txt_query = (EditText)findViewById(R.id.txt_title);
        EditText txt_limit = (EditText)findViewById(R.id.txt_limit);

        String title = txt_query.getText().toString().replace(' ','+');
        int limit = txt_limit.getText().toString().equals("") ? 10 : Integer.parseInt(txt_limit.getText().toString());

        Intent intent = new Intent(this.getApplicationContext(), BookListActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("limit", limit);

        this.startActivity(intent);
    }
}
