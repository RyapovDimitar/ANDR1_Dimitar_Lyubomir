package com.example.andr1.fontysappliation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by az on 30.3.2017 г..
 */

public class DataListAdapterGrades extends BaseAdapter {

    // global reference of expected data type, String[]
    private GradesElement[] mDataList;

    // global LayoutInflater reference
    private LayoutInflater layoutInflater;

    public DataListAdapterGrades(Context mContext, GradesElement[] mDataList) {

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
    public GradesElement getItem(int position) {

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
        if (convertView == null) {

            // if it is new, initialise it using 'adapter_row' layout
            convertView = layoutInflater.inflate(R.layout.adapter_row_grade, parent, false);
        }

        // retrieve TextView with the id 'textView4'
        TextView tvDate = (TextView) convertView.findViewById(R.id.textView5);
        TextView tvItemCode = (TextView) convertView.findViewById(R.id.textView4);
        TextView tvGrade = (TextView) convertView.findViewById(R.id.textView6);

        tvDate.setText(getItem(position).date);

        // get the appropriate data from the array using position index
        // and set it to tvName TextView
        tvItemCode.setText(getItem(position).itemCode);
        tvGrade.setText(getItem(position).grade);
        //tvSubject.setText(getItem(position).room);

        // return the view
        return convertView;
    }
}
