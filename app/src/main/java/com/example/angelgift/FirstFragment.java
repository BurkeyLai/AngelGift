package com.example.angelgift;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelgift.RoomDataBase.DataBase;
import com.example.angelgift.RoomDataBase.MyData;

public class FirstFragment extends Fragment {
    MyAdapter myAdapter;

    private RecyclerView recyclerView;
    private TextView mTextTitle;
    private CalendarView calendar;
    private SimpleDateFormat sdf;
    private static String selectedDate;

    public FirstFragment() {
        // Requires empty public constructor
    }

    //MyAdapter myAdapter;
    //MyData nowSelectedData;//取得在畫面上顯示中的資料內容

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_first, container, false);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)// If we need to use getContext() inside here...
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendar = (CalendarView) getView().findViewById(R.id.calendarView);
        sdf = new SimpleDateFormat("yyyyMMdd");
        selectedDate = sdf.format(new Date(calendar.getDate()));
        ((MainActivity)getActivity()).setSelectedDate(selectedDate); // Pass value back to MainActivity.

        recyclerView = getView().findViewById(R.id.recyclerView_dailyData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));//設置分隔線
        setRecyclerFunction(recyclerView);//設置RecyclerView左滑刪除

        /** This part is implemented in onResume()
         new Thread(() -> {
         List<MyData> data = DataBase.getInstance(getContext()).getDataUao().findDataByDate(selectedDate); // Find the data belonging to this date.
         myAdapter = new MyAdapter(getActivity(), data);
         getActivity().runOnUiThread(() -> {
         recyclerView.setAdapter(myAdapter);
         myAdapter.setOnItemClickListener(myData -> ((MainActivity)getActivity()).checkSelectedItem(selectedDate, myData.getClass_id())); // Connect the adapter's external interface.
         // As the item in adapter is clicked...
         });
         }).start();

         **/

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) { // When clicked date in calendar is changed...

                String m = (month + 1 < 10) ? "0" + (month + 1) : Integer.toString(month + 1);
                String d = (dayOfMonth < 10) ? "0" + dayOfMonth : Integer.toString(dayOfMonth);
                selectedDate = year + m + d;
                Log.i("Selected Date", selectedDate);
                ((MainActivity)getActivity()).setSelectedDate(selectedDate); // Pass value back to MainActivity.
                myAdapter.refreshView(); // Refresh the RecycleView and shows the data belonging to the selected date.
            }
        });
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<MyData> myData;
        private final Activity activity;
        private OnItemClickListener onItemClickListener;

        public MyAdapter(Activity activity, List<MyData> myData) {
            this.activity = activity;
            this.myData = myData;
        }

        //建立對外接口
        public interface OnItemClickListener {
            void onItemClick(MyData myData);
        }

        //建立對外接口
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            View view;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(android.R.id.text1);
                view = itemView;
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            if (myData.get(position).getClass_id() == 0) { // Convert the class ID (int) to its name (String).
                holder.tvTitle.setText(holder.itemView.getContext().getString(R.string.class_1));
            } else if (myData.get(position).getClass_id() == 1) {
                holder.tvTitle.setText(holder.itemView.getContext().getString(R.string.class_2));
            } else if (myData.get(position).getClass_id() == 2) {
                holder.tvTitle.setText(holder.itemView.getContext().getString(R.string.class_3));
            }
            holder.view.setOnClickListener((v) -> {
                onItemClickListener.onItemClick(myData.get(position));
            });
        }

        @Override
        public int getItemCount() {
            return myData.size();
        }

        //更新資料
        public void refreshView() {
            new Thread(() -> {
                //List<MyData> data = DataBase.getInstance(activity).getDataUao().displayAll();
                List<MyData> data = DataBase.getInstance(activity).getDataUao().findDataByDate(selectedDate);
                this.myData = data;
                activity.runOnUiThread(() -> {
                    notifyDataSetChanged();
                });
            }).start();
        }

        //刪除資料
        public void deleteData(int position) {
            new Thread(() -> {
                DataBase.getInstance(activity).getDataUao().deleteDataByDateAndClassID(selectedDate, myData.get(position).getClass_id());
                activity.runOnUiThread(() -> {
                    notifyItemRemoved(position);
                    refreshView();
                });
            }).start();
        }
    }

    /**設置RecyclerView的左滑刪除行為*/
    private void setRecyclerFunction(RecyclerView recyclerView){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {//設置RecyclerView手勢功能
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                switch (direction){
                    case ItemTouchHelper.LEFT:
                    case ItemTouchHelper.RIGHT:
                        myAdapter.deleteData(position);
                        break;
                }
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() { // Keep the RecycleView to show the newest data in Fragment's onResume state.
        super.onResume();
        new Thread(() -> {
            myAdapter = new MyAdapter(getActivity(), DataBase.getInstance(getContext()).getDataUao().findDataByDate(selectedDate));
            getActivity().runOnUiThread(() -> {
                recyclerView.setAdapter(myAdapter);
                myAdapter.setOnItemClickListener(myData -> ((MainActivity)getActivity()).checkSelectedItem(selectedDate, myData.getClass_id())); // Connect the adapter's external interface.
                // As the item in adapter is clicked...
            });
        }).start();
    }
}
