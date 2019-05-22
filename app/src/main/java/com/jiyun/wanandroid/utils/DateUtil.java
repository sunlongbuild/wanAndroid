package com.jiyun.wanandroid.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jiyun.wanandroid.R;

/**
 * Created by $sl on 2019/5/22 10:29.
 */
public class DateUtil {
    public static void getDateTime(Context mContext, final TextView textView) {
        View date_time_picker = View.inflate(mContext, R.layout.layout_date_picker, null);
        final DatePicker datePicker = (DatePicker) date_time_picker.findViewById(R.id.data_picker);

        //   Build   DateTimeDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(date_time_picker);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int month = datePicker.getMonth();
                int newMonth = month + 1;
                String dateStr = datePicker.getYear() + "-" + newMonth + "-" + datePicker.getDayOfMonth();
//                int currentMinute = timePicker.getCurrentMinute();
//                String curMinute = "";
//                if (currentMinute < 10) {
//                    curMinute = "0" + currentMinute;
//                } else {
//                    curMinute = String.valueOf(currentMinute);
//                }
//                String timeStr = timePicker.getCurrentHour() + ":" + curMinute;
                textView.setText(dateStr);
            }
        });
        builder.show();
    }
}
