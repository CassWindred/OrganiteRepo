package com.example.tom.Organite;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class AddLesson extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

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
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SubjsStringArray);
        dropdown.setAdapter(adapter);
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
}
