package com.example.angelgift;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClassSelectionActivity extends AppCompatActivity {

    private String class_selected = "";
    private Button mSelectedButton;
    public static final String CLASS_SELECTED =
            "com.example.angelgift.extra.REPLY";

    private Intent replyIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_selection);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void classClicked(View view) {

        mSelectedButton = (Button) findViewById(view.getId());
        class_selected = mSelectedButton.getText().toString();

        switch (view.getId()) {
            case R.id.button_class_1:
                displayToast(class_selected + " is selected!");
                break;
            case R.id.button_class_2:
                displayToast(class_selected + " is selected!");
                break;
            case R.id.button_class_3:
                displayToast(class_selected + " is selected!");
                break;
        }
    }

    public void returnClassSelected(View view) {

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        if (class_selected == "") {
            displayToast("Please select one class!");
        } else {
            replyIntent = new Intent();
            replyIntent.putExtra(CLASS_SELECTED, class_selected);
            setResult(RESULT_OK, replyIntent);
            finish();
        }
    }
}