package com.example.reem.rmaarouf_habittracker;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


/*
Activity class that shows all the completed habits and the dates they are completed on. A user is able to press
on the habit to delete it. A dialog will pop up and prompt the user to either cancel or delete habit.
If habit is deleted, the new HabitList is saved into file.
 */
public class ViewCompletedHabitsActivity extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener{

    private ListView completedHabitList;
    private static HabitList habitList = new HabitList();
    private ArrayAdapter<Habit> adapter;
    private Habit habit;
    private static ArrayList<Habit> completedHabits = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_completed_habits);
        habitList.setHabits(FileManager.loadFromFile(this));
        ArrayList<Habit> completedHabits = setCompletedList();


        completedHabitList = (ListView) findViewById(R.id.listView2);

        // Code taken from Lonely Twitter Repo from github: https://github.com/watts1/lonelyTwitter Oct 1, 2016
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, completedHabits);
        completedHabitList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        completedHabitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                habit = (Habit) parent.getItemAtPosition(position);

                // Code taken from https://developer.android.com/guide/topics/ui/dialogs.html#DialogFragment Oct 1,2016
                DialogFragment dialog = new NoticeDialogFragment();
                dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
            }
        });


    }


    /*
    Finds all Habits that have habit completion dates
     */
    public ArrayList<Habit> setCompletedList()
    {
        ArrayList<Habit> completedHabits = new ArrayList<Habit>();
        for (Habit habit: habitList.getHabits())
        {
            if (habit.getCompletedDates().size()>0)
            {
                for (Date date: habit.getCompletedDates())
                {
                    habit.setDate(date);
                    habit.setComplete(true);
                    completedHabits.add(habit);
                }
            }
        }


        return completedHabits;
    }

    /*
    Deletes habit from the completed habit list
     */
    // Knowledge to create a positive click dialog from https://developer.android.com/guide/topics/ui/dialogs.html#DialogFragment Oct 1, 2016
    @Override
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        System.out.print(habitList);

        Toast.makeText(getApplicationContext(),
                "Habit deleted: " + habit.getName(),
                Toast.LENGTH_LONG).show();


        habitList.deleteCompletion(habit);
        FileManager.saveToFile(ViewCompletedHabitsActivity.this,habitList);
        completedHabits = setCompletedList();
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, completedHabits);
        completedHabitList.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {

    }


    @Override
    protected void onResume()
    {
        super.onResume();

    }
}
