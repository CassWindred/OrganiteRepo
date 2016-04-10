package com.example.tom.Organite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainMenu extends ActionBarActivity {

   SharedPreferenceSubjects ObjSharedPreferenceSubjects = new SharedPreferenceSubjects();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void moveToAddHomework(View view) {
        startActivity(new Intent(this, AddHomework.class)); //Moves to the AddHomework activity when the Add Homework button is clicked
    }

    public void moveToAddLesson(View view) {
        SharedPreferenceSubjects SavedSubjs = new SharedPreferenceSubjects();
        if (SavedSubjs.loadSubjects(this) == null) { //Checks if any subjects have been created
            Toast.makeText(this, "There are no Subjects, please add one or more before adding lessons", Toast.LENGTH_LONG).show();


        }
        else {
            startActivity(new Intent(this, AddLesson.class)); //Starts the AddLesson activity
        }

    }

    public  void moveToAddSubject(View view) {
        startActivity(new Intent(this, AddSubject.class)); //Starts the AddSubject activity
    }
}
