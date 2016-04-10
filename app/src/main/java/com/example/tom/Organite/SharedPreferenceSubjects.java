package com.example.tom.Organite;

/**
 * Created by Tom on 29/03/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//This class handles saving and retrieving the Arraylist of Subject Object from a sharedpreferences file
//This requires a considerable quantity of code as well as the gson library because the sharedpreference file only contains strings
//The gson library contains methods for converting between "json" formatted strings and arraylists which may contain objects
public class SharedPreferenceSubjects {
    public static final String PREFS_NAME = "NKDROID_APP";
    public static final String SUBJECTS = "Subject";
    public SharedPreferenceSubjects() {
        super();
    }
    public void storeSubjects(Context context, List favorites) {
// used to store arrayList in json format
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(SUBJECTS, jsonFavorites);
        editor.commit();
    }
    public ArrayList loadSubjects(Context context) {
// used for retrieving arraylist from json formatted string
        SharedPreferences settings;
        List favorites;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        if (settings.contains(SUBJECTS)) {
            String jsonFavorites = settings.getString(SUBJECTS, null);
            Gson gson = new Gson();
            SubjectClass[] favoriteItems = gson.fromJson(jsonFavorites,SubjectClass[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList(favorites);
        } else
            return null;
        return (ArrayList) favorites;
    }
    public void addSubject(Context context, SubjectClass beanSampleList) {
        //adds a subject object to the existing arraylist
        List subjects = loadSubjects(context);
        if (subjects == null)
            subjects = new ArrayList();
        subjects.add(beanSampleList);
        storeSubjects(context, subjects);
    }
    public void removeSubject(Context context, SubjectClass beanSampleList) { //removes the most recently added subject from the saved arraylist
        ArrayList favorites = loadSubjects(context);
        if (favorites != null) {
            favorites.remove(beanSampleList);
            storeSubjects(context, favorites);
        }
    }
}