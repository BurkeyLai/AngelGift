package com.example.angelgift;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SecondFragment extends Fragment {
    private TextView mTextTitle;

    public SecondFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_second, container, false);
        mTextTitle = (TextView) root.findViewById(R.id.text_title_second);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mTextTitle.setText("Hell Hydra!");
        /*
        mTextTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onOpenDetail("Open from Second Fragment!");
            }
        });*/
    }
}
