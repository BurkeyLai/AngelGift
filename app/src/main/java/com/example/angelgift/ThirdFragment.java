package com.example.angelgift;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

//import static com.example.angelgift.MainActivity.DETAIL_MESSAGE;

public class ThirdFragment extends Fragment {

    private TextView mTextTitle;

    public ThirdFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_third, container, false);
        mTextTitle = (TextView) root.findViewById(R.id.text_title_third);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextTitle.setText("Yippee Ki-Yay!");
    }
}
