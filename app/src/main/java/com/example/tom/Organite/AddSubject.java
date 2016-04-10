package com.example.tom.Organite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddSubject extends AppCompatActivity {
    Button doneButton;
    EditText EditName;
    EditText EditTeacher;
    EditText EditRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        //Initializes all the buttons and textviews
        doneButton = (Button) findViewById(R.id.buttonDone);
        EditName = (EditText) findViewById(R.id.editTextName);
        EditTeacher = (EditText) findViewById(R.id.editTextTeacher);
        EditRoom = (EditText) findViewById(R.id.editTextRoom);
    }

    public void doneButtonPressed(View view){
        String Name = EditName.getText().toString();
        String Teacher = EditTeacher.getText().toString();
        String Room = EditRoom.getText().toString();

        SubjectClass Subj= new SubjectClass(Name,Teacher,Room); //Creates a new subject object with the input given
        new SharedPreferenceSubjects().addSubject(this, Subj); //Saves the subject object to the sharedpreferences file on the device
        Toast subjectSavedToast = Toast.makeText(getApplicationContext(), "Subject (should be) saved to device!", Toast.LENGTH_SHORT);
        subjectSavedToast.show(); //Displays confirmation message to suer
        startActivity(new Intent(this, MainMenu.class)); //Returns to main menu
    }

}
