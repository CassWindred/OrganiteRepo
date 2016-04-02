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


public class SharedPreferenceSubjects {
    public static final String PREFS_NAME = "NKDROID_APP";
    public static final String FAVORITES = "Favorite";
    public SharedPreferenceSubjects() {
        super();
    }
    public void storeSubjects(Context context, List favorites) {
// used for store arrayList in json format
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(FAVORITES, jsonFavorites);
        editor.commit();
    }
    public ArrayList loadSubjects(Context context) {
// used for retrieving arraylist from json formatted string
        SharedPreferences settings;
        List favorites;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            SubjectClass[] favoriteItems = gson.fromJson(jsonFavorites,SubjectClass[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList(favorites);
        } else
            return null;
        return (ArrayList) favorites;
    }
    public void addSubject(Context context, SubjectClass beanSampleList) {
        List subjects = loadSubjects(context);
        if (subjects == null)
            subjects = new ArrayList();
        subjects.add(beanSampleList);
        storeSubjects(context, subjects);
    }
    public void removeFavorite(Context context, SubjectClass beanSampleList) {
        ArrayList favorites = loadSubjects(context);
        if (favorites != null) {
            favorites.remove(beanSampleList);
            storeSubjects(context, favorites);
        }
    }
}