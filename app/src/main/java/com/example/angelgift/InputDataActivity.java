package com.example.angelgift;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InputDataActivity extends AppCompatActivity {

    private TextView mTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);

        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mTextTitle = (TextView) findViewById(R.id.text_title_inputdata);
        mTextTitle.setText(selectedDate);
    }
}
