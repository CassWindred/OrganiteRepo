package com.example.tom.Organite;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class clocktimepickerfragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Creates an
        Toast.makeText(getActivity(),"Sent Broadcast", Toast.LENGTH_SHORT).show(); //Shows a confirmation message
        //Creates an intent with the hour and minute chosen
        Intent TimeChosen = new Intent("TimePickerBroadcast");
        TimeChosen.putExtra("hourOfDay", hourOfDay);
        TimeChosen.putExtra("minute", minute);
        //Broadcasts the intent to AddLeson
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(TimeChosen);
    }
}