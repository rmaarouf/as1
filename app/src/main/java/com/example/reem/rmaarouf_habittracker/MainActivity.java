/*

rmaarouf-HabitTracker: Records and updates daily habits.

Copyright 2016 Reem Maarouf rmaarouf@ualberta.ca

Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.


*/



package com.example.reem.rmaarouf_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private HabitList habitList = new HabitList();
    // Code taken from Lonely Twitter from github: https://github.com/watts1/lonelyTwitter Sept 22, 2016
    private ListView oldHabitsList;
    private ArrayAdapter<Habit> adapter;

    private Habit habit;



    /*
        Creates the main activity with a add habit button, view completions button and a spinner that includes the
        days of the week. Habits will be listed on the main page.

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FileManager.saveToFile(this,habitList);
        habitList.setHabits(FileManager.loadFromFile(this));

        oldHabitsList = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, habitList.getHabits());
        oldHabitsList.setAdapter(adapter);

        final Button addNewHabit = (Button) findViewById(R.id.addNewHabit);
        Spinner spinner = (Spinner) findViewById(R.id.daysSpinner);


        Button viewCompletions = (Button) findViewById(R.id.viewCompletions);

        // Code taken from Lonely Twitter from github: https://github.com/watts1/lonelyTwitter Sept 22, 2016
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.days_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        addNewHabit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println(habitList.getHabits());
                addNewHabit(v);
            }
        });

        viewCompletions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewCompletedHabits(v);
            }
        });

        // Code taken from http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview Sept 28,2016
        oldHabitsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Habit habit = (Habit) parent.getItemAtPosition(position);
                Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
                // Then you start a new Activity via Intent

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HabitDetailActivity.class);
                intent.putExtra("Habit", habit);
                startActivity(intent);


            }
        });

        // Code and Knowledge of Spinner gained from https://developer.android.com/guide/topics/ui/controls/spinner.html
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String day = (String) parent.getItemAtPosition(position);
                if (day.equals("All Habits"))
                {
                    adapter = new ArrayAdapter<Habit>(MainActivity.this,
                            R.layout.list_item, habitList.getHabits());
                    oldHabitsList.setAdapter(adapter);
                }
                else{
                    ArrayList<Habit> habitsPerDay = new ArrayList<Habit>();
                    for (Habit habit: habitList.getHabits())
                    {
                        if (habit.getDaysOfWeek().contains(day))
                        {
                            habitsPerDay.add(habit);
                        }
                    }
                    adapter = new ArrayAdapter<Habit>(MainActivity.this,
                            R.layout.list_item, habitsPerDay);
                    oldHabitsList.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    /*
    Resumes activity and loads contents of file for main activity
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        //adapter.notifyDataSetChanged();

        // Code taken from Lonely Twitter from github: https://github.com/watts1/lonelyTwitter Sept 22, 2016
        habitList.setHabits(FileManager.loadFromFile(this));
        oldHabitsList = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, habitList.getHabits());
        oldHabitsList.setAdapter(adapter);

    }

    /*
    Creates intent for AddNewHabitAcitivty
     */

    private void addNewHabit(View view)
    {
        Intent intent = new Intent(this, AddNewHabitActivity.class);
        startActivity(intent);
    }


    /*
    Creates intent for ViewCompletedHabitsActivity
     */
    private void viewCompletedHabits(View view)
    {
        Intent intent = new Intent(this, ViewCompletedHabitsActivity.class);
        startActivity(intent);
    }




}
