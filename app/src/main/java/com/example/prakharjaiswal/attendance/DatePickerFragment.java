package com.example.prakharjaiswal.attendance;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Prakhar Jaiswal on 01-07-2017.
 */

public class DatePickerFragment extends DialogFragment {
    DatePickerDialog.OnDateSetListener onDateSetListener;
    private int year, month, day;

    public DatePickerFragment() {
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener onDateSetListener1){
        onDateSetListener = onDateSetListener1;
    }

    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args)
    {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
    }
}
