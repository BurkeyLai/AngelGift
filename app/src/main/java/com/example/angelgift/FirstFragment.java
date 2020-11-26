package com.example.angelgift;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;

public class FirstFragment extends Fragment {
    private TextView mTextTitle;
    private CalendarView calendar;
    private SimpleDateFormat sdf;
    private String selectedDate;

    public FirstFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_first, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendar = (CalendarView) getView().findViewById(R.id.calendarView);
        sdf = new SimpleDateFormat("yyyy / MM / dd");
        selectedDate = sdf.format(new Date(calendar.getDate()));
        ((MainActivity)getActivity()).setSelectedDate(selectedDate); // Pass value back to MainActivity.

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                selectedDate = String.valueOf(year + " / " + month + " / " + dayOfMonth);
                Log.i("Selected Date", selectedDate);
                ((MainActivity)getActivity()).setSelectedDate(selectedDate); // Pass value back to MainActivity.
            }
        });

        /*
        mTextTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onOpenDetail("Open from First Fragment!");
            }
        });*/
    }


}
