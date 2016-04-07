package com.example.tom.Organite;

import android.app.AlarmManager;
import android.app.DialogFragment;
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


public class AddLesson extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    public Spinner dropdown;
    public String LastChosenSpinnerItem;
    public int setHourOfDay;
    public int setminute;
    public Context AddLessonContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

}

