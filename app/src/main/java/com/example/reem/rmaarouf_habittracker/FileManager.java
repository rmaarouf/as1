package com.example.reem.rmaarouf_habittracker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Reem on 2016-09-29.
 */
public class FileManager
{

    // Code taken from Lonely Twitter from github: https://github.com/watts1/lonelyTwitter Sept 22, 2016
    private static final String FILENAME = "file.sav";



    // Code taken from Lonely Twitter from github: https://github.com/watts1/lonelyTwitter Sept 22, 2016
    public static ArrayList<Habit> loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Code taken from stackOverFlow http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept 22,2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            return gson.fromJson(in, listType);


        } catch (IOException e) {
            // TODO Auto-generated catch block

            return new ArrayList<Habit>();
            //throw new RuntimeException();
        }


    }


    // Code taken from Lonely Twitter from github: https://github.com/watts1/lonelyTwitter Sept 22, 2016
    public static void saveToFile(Context context,HabitList habits)
    {

        try {

            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habits.getHabits(), writer);
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();

        }
    }

}
