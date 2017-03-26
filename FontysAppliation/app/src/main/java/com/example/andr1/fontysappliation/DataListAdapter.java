package com.example.andr1.fontysappliation;

/**
 * Created by Lubomir on 3/26/2017.
 */


        import android.content.Context;
        import android.icu.text.DateFormat;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

public class DataListAdapter extends BaseAdapter {

    // global reference of expected data type, String[]
    private ScheduleElement[] mDataList;

    // global LayoutInflater reference
    private LayoutInflater layoutInflater;

    public DataListAdapter(Context mContext, ScheduleElement[] mDataList) {

        // set the reference to data passed
        this.mDataList = mDataList;

        // set the reference of LayoutInflater form the main context
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // helper function
    @Override
    public int getCount() {

        return mDataList.length;
    }

    // helper function
    @Override
    public ScheduleElement getItem(int position) {

        return mDataList[position];
    }

    // helper function
    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check if the view being just created, ot it is the one which went out
        if(convertView == null){

            // if it is new, initialise it using 'adapter_row' layout
            convertView = layoutInflater.inflate(R.layout.adapter_row, parent, false);
        }

        // retrieve TextView with the id 'textView4'
        TextView tvSubject = (TextView) convertView.findViewById(R.id.textView5);
        TextView tvRoom = (TextView) convertView.findViewById(R.id.textView4);
        TextView tvStart = (TextView) convertView.findViewById(R.id.textView6);

        tvSubject.setText(getItem(position).subject);

        // get the appropriate data from the array using position index
        // and set it to tvName TextView
        tvRoom.setText(getItem(position).room);
        tvStart.setText(getItem(position).start.toString());
        //tvSubject.setText(getItem(position).room);

        // return the view
        return convertView;
    }
}
