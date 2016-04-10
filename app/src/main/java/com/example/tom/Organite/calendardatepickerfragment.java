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
        return new DatePickerDialog(getActivity(), this, yy, mm, dd); //Initializes the datepicker to the current date
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        //Creates an intent with all the information on the chosen date
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("set-date");
        intent.putExtra("input-day", Integer.toString(dd));
        intent.putExtra("input-month", Integer.toString(mm));
        intent.putExtra("input-year", Integer.toString(yy));
        //Sends a broadcast with the intent to the AddHomework class (Or any other class which has the relevant broadcast receiver)
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

}