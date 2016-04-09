package com.example.tom.Organite;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class AddLesson extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    public Spinner dropdown;
    public String LastChosenSpinnerItem;
    public int setHourOfDay;
    public int setminute;
    public Context AddLessonContext;
    public boolean[] choDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        choDays=new boolean[8];
        AddLessonContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        dropdown = (Spinner)findViewById(R.id.spinner);
        dropdown.setOnItemSelectedListener(this);

        SharedPreferenceSubjects SavedSubjs = new SharedPreferenceSubjects();
        ArrayList SubjsArrayList = new ArrayList(SavedSubjs.loadSubjects(this));
        String[] SubjsStringArray;
        SubjsStringArray = new String[SubjsArrayList.size()];
        for ( int i=0; i<SubjsArrayList.size(); i++){ //This might cause it to cut off the last option or add an extra
            SubjsStringArray[i]="Default";
            SubjectClass Subj = ((SubjectClass) SubjsArrayList.get(i));
            SubjsStringArray[i]=Subj.Getname();
        }
        //Creating and sorting out the drop down menu

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SubjsStringArray);
        dropdown.setAdapter(adapter);

        LocalBroadcastManager.getInstance(this).registerReceiver(TimeReceiver, new IntentFilter("TimePickerBroadcast"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_lesson, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void doneButtonPressed(View view){
        String ToastText = "The Selected Item is: " + LastChosenSpinnerItem;
        Toast.makeText(this,ToastText, Toast.LENGTH_SHORT).show();
        AlarmManager alarmMgr = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        Calendar timeTill = Calendar.getInstance();
        Intent alarmIntent;
        PendingIntent pendingAlarmIntent;
        for (int i=1; i<8; i++){
            if (choDays[i]){
                //sets a calendar instance to the date and time the lesson is
                alarmIntent = new Intent(AddLesson.this, lessonAlarmService.class);
                alarmIntent.putExtra("Subject", LastChosenSpinnerItem);
                pendingAlarmIntent = PendingIntent.getService(AddLesson.this, 0, alarmIntent, 0);
                int days = i + (7 - timeTill.DAY_OF_WEEK);
                timeTill.add(Calendar.DATE, days);
                timeTill.set(Calendar.HOUR, setHourOfDay);
                timeTill.set(Calendar.MINUTE, setminute);
                timeTill.set(Calendar.SECOND, 0);
                alarmMgr.setRepeating(alarmMgr.RTC_WAKEUP, timeTill.getTimeInMillis(), alarmMgr.INTERVAL_DAY * 7, pendingAlarmIntent);
            }
        }



    }
    public void timeButtonPressed(View view){
        DialogFragment newFragment = new clocktimepickerfragment();
        newFragment.show(getFragmentManager(), "timeFrag");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        LastChosenSpinnerItem = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public BroadcastReceiver TimeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setHourOfDay = intent.getIntExtra("hourOfDay", 0);
            setminute = intent.getIntExtra("minute", 0);
            String ReadableResult = "Hour: "+setHourOfDay+" Minute: "+setminute;
            Toast.makeText(AddLessonContext, ReadableResult, Toast.LENGTH_SHORT).show();
        }
    };
    public void monCheck(View view){
        choDays[2]=!choDays[2];
    }
    public void tueCheck(View view){
        choDays[3]=!choDays[3];
    }

    public void wedCheck(View view){
        choDays[4]=!choDays[4];
    }
    public void thuCheck(View view){
        choDays[5]=!choDays[5];
    }
    public void friCheck(View view){
        choDays[6]=!choDays[6];
    }
    public void satCheck(View view){
        choDays[7]=!choDays[7];
    }
    public void sunCheck(View view){
        choDays[1]=!choDays[1];
    }

}

