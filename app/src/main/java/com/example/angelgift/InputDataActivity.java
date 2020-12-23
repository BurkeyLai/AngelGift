package com.example.angelgift;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import com.example.angelgift.RoomDataBase.DataBase;
import com.example.angelgift.RoomDataBase.MyData;
import com.facebook.stetho.Stetho;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class InputDataActivity extends AppCompatActivity {

    MyData nowSelectedData;//取得在畫面上顯示中的資料內容

    private TextView mTextTitle;
    private TextView mTextClass;
    private EditText mEditText1;
    private EditText mEditText2;
    private TextView mTextResult1;
    private TextView mTextResult2;
    private TextView mTextResult3;
    private Button mBtStore;

    private Intent classSelectionIntent;

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        Bundle extras = getIntent().getExtras();
        //String selectedDate = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mTextTitle = (TextView) findViewById(R.id.text_title_inputdata);
        mTextClass = (TextView) findViewById(R.id.class_selection);
        mEditText1 = (EditText) findViewById(R.id.edit_1);
        mEditText2 = (EditText) findViewById(R.id.edit_2);
        mTextResult1 = (TextView) findViewById(R.id.result_1);
        mTextResult2 = (TextView) findViewById(R.id.result_2);
        mTextResult3 = (TextView) findViewById(R.id.result_3);
        mBtStore = (Button) findViewById(R.id.button_store);

        classSelectionIntent = new Intent(this, ClassSelectionActivity.class);
        mTextClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(classSelectionIntent, TEXT_REQUEST);
            }
        });

        if (extras.getString("EXTRA_MODE").equals("INPUT_MODE")) { // Condition for input new data.
            mTextTitle.setText(extras.getString("EXTRA_DATE"));
            /**新增資料*/
            mBtStore.setOnClickListener((v -> {

                if (mTextClass.getText().toString().equals("Class Selection") ||
                        mEditText1.getText().toString().length() == 0 ||
                        mEditText2.getText().toString().length() == 0) {
                    displayToast("Please input Class, EditText 1 and EditText 2!");
                    //return;//如果名字欄沒填入任何東西，則不執行下面的程序
                } //else if (DataBase.getInstance(this).getDataUao().findDataByDateAndClassID(selectedDate, getClassId(mTextClass.getText().toString())).size() > 0) {
                //  displayToast("Data belonging to " + mTextClass.getText().toString() + " is already exist!");
                //}
                else {
                    new Thread(() -> {
                        String date = mTextTitle.getText().toString();
                        int class_id = getClassId(mTextClass.getText().toString());
                        int number1 = Integer.parseInt(mEditText1.getText().toString());
                        int number2 = Integer.parseInt(mEditText2.getText().toString());
                        int number3 = number1 + number2;
                        int number4 = 0;
                        int number5 = 0;

                        MyData data = new MyData(date, class_id, number1, number2, number3, number4, number5);
                        DataBase.getInstance(this).getDataUao().insertData(data);

                        runOnUiThread(() -> {
                            //myAdapter.refreshView();
                            mEditText1.setText("");
                            mEditText2.setText("");
                            mTextResult1.setText("");
                            mTextResult2.setText("");
                            mTextResult3.setText("");
                        });
                    }).start();
                }
            }));
        } else if (extras.getString("EXTRA_MODE").equals("MODIFY_MODE")) { // Condition for clicking the item in RecycleView.
            String selectedDate = extras.getString("EXTRA_DATE");
            int selectedClassId = extras.getInt("EXTRA_CLASSID");
            mTextTitle.setText(selectedDate);
            mTextClass.setText(getClassName(selectedClassId));

            new Thread(() -> {
                //String date = mTextTitle.getText().toString();
                //int class_id = getClassId(mTextClass.getText().toString());
                //int number1 = Integer.parseInt(mEditText1.getText().toString());
                //int number2 = Integer.parseInt(mEditText2.getText().toString());
                //int number3 = number1 + number2;
                //int number4 = 0;
                //int number5 = 0;

                //MyData data = new MyData(date, class_id, number1, number2, number3, number4, number5);
                //DataBase.getInstance(this).getDataUao().insertData(data);
                nowSelectedData = DataBase.getInstance(this).getDataUao().findDataByDateAndClassID(selectedDate, selectedClassId).get(0); // findDataByDateAndClassID return List<MyData>

                runOnUiThread(() -> {
                    mEditText1.setText(Integer.toString(nowSelectedData.getNumber1()));
                    mEditText2.setText(Integer.toString(nowSelectedData.getNumber2()));
                    mTextResult1.setText(Integer.toString(nowSelectedData.getNumber3()));
                    mTextResult2.setText(Integer.toString(nowSelectedData.getNumber4()));
                    mTextResult3.setText(Integer.toString(nowSelectedData.getNumber5()));
                });
            }).start();

        }

    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String class_selected = data.getStringExtra(ClassSelectionActivity.CLASS_SELECTED);
                mTextClass.setText(class_selected);
                mTextClass.setVisibility(View.VISIBLE);
            }
        }
    }

    private int getClassId(String s) { // Convert the class name (string) to its belonging ID (int).
        if (getString(R.string.class_1).equals(s)) {
            return 0;
        } else if (getString(R.string.class_2).equals(s)) {
            return 1;
        } else if (getString(R.string.class_3).equals(s)) {
            return 2;
        }
        throw new IllegalStateException("Unexpected value: " + s);
    }

    private String getClassName(int id) { // Convert the class ID (int) to its name (String).
        if (id == 0) {
            return getString(R.string.class_1);
        } else if (id == 1) {
            return getString(R.string.class_2);
        } else if (id == 2) {
            return getString(R.string.class_3);
        }
        throw new IllegalStateException("Unexpected value: " + id);
    }
}
