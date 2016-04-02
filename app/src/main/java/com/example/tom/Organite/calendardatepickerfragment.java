package com.example.tom.Organite;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class calendardatepickerfragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("set-date");
        // You can also include some extra data.
        intent.putExtra("input-day", Integer.toString(dd));
        intent.putExtra("input-month", Integer.toString(mm));
        intent.putExtra("input-year", Integer.toString(yy));
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        //Oh really
    }

}