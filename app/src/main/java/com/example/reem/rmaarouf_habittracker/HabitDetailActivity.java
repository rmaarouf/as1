package com.example.reem.rmaarouf_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HabitDetailActivity extends AppCompatActivity {


    private static HabitList habitList = new HabitList();

    /*
    Activity starts and displays the records for the habit that is pressed in the main activity. User will be
    able to complete an habit or delete an habit. If habit is completed, user will be shown when habit
    is completed and the amount of times that habit is completed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);

        Intent intent = getIntent();
        final Habit habit = (Habit)intent.getSerializableExtra("Habit");


        TextView habitName = (TextView) findViewById(R.id.HabitName);
        TextView habitDate = (TextView) findViewById(R.id.HabitDate);
        final TextView habitCompletions = (TextView) findViewById(R.id.numberOfCompletions);
        final TextView lastCompletion = (TextView) findViewById(R.id.lastCompletion);

        Button deleteHabit = (Button) findViewById(R.id.deleteHabitButton);
        Button completeHabit = (Button) findViewById(R.id.completeHabitButton);
        Button back = (Button) findViewById(R.id.backFromDetail);


        TextView daysOfWeek = (TextView) findViewById(R.id.days);

        // Knowledge gained from https://developer.android.com/reference/android/widget/TextView.html Sept 24, 2016
        habitName.setTextSize(25);
        habitName.setText(habit.getName());
        habitDate.setTextSize(20);
        habitDate.setText("Started on date: "+habit.getDate().toString());
        habitCompletions.setTextSize(20);
        habitCompletions.setText("Number of Completions:"+Integer.toString(habit.getNumberOfCompletions()));
        lastCompletion.setTextSize(20);


        if (habit.getCompletedDates().size()>0)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
            Date compDate = habit.getCompletedDates().get(habit.getCompletedDates().size()-1);
            lastCompletion.setText("Completed on: "+sdf.format(compDate));
        }
        else
        {
            lastCompletion.setText("");
        }

        daysOfWeek.setTextSize(20);
        String daysInString = "";
        for (String day: habit.getDaysOfWeek())
        {
            daysInString = daysInString+" "+day;
        }
        daysOfWeek.setText("Days Set on: "+ daysInString);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitList.setHabits(FileManager.loadFromFile(HabitDetailActivity.this));
                habitList.delete(habit);
                FileManager.saveToFile(HabitDetailActivity.this, habitList);
                finish();
            }
        });

        completeHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitList.setHabits(FileManager.loadFromFile(HabitDetailActivity.this));
                habitList.complete(habit);
                FileManager.saveToFile(HabitDetailActivity.this, habitList);

                habit.setNumberOfCompletions(habit.getNumberOfCompletions()+1);
                habitCompletions.setText("Number of Completions:"+Integer.toString(habit.getNumberOfCompletions()));
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
                lastCompletion.setText("Completed on: "+sdf.format(date));
            }
        });


    }
}
