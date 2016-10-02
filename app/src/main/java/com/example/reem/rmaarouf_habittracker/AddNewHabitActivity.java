package com.example.reem.rmaarouf_habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddNewHabitActivity extends AppCompatActivity {


    private EditText dateText;
    private EditText nameText;
    private HabitList habitList = new HabitList();


    /*
    Creates a form for adding new activity with a date, name and days of week. Users have an option to fill in what they want.
    Once add button is pressed, the new habit it saved to a file and activity is finished. Returns to main
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_habit);

        dateText = (EditText) findViewById(R.id.dateInput);
        nameText = (EditText) findViewById(R.id.habitNameInput);

        habitList.setHabits(FileManager.loadFromFile(this));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateText.setText(sdf.format(new Date()));

        Button addHabits = (Button) findViewById(R.id.addHabitSend);
        Button backButton = (Button) findViewById(R.id.back);

        addHabits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(dateText.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String nameOfHabit = nameText.getText().toString();
                ArrayList<String> daysOfWeek = getDaysOfWeek();

                Habit habit = new Habit(date, nameOfHabit, daysOfWeek);
                habitList.add(habit);
                FileManager.saveToFile(AddNewHabitActivity.this,habitList);

                finish();
            }

        });

        backButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                finish();
            }
        });
    }

        /*
        Gets the days of the week that are checked in the form.
         */

        public ArrayList<String> getDaysOfWeek()
        {
            ArrayList<String> daysOfWeek = new ArrayList<String>();

            CheckBox monCheckBox = (CheckBox) findViewById(R.id.ModcheckBox);
            CheckBox tuesCheckBox = (CheckBox) findViewById(R.id.TuesCheckBox);
            CheckBox wedCheckBox = (CheckBox) findViewById(R.id.WedCheckBox);
            CheckBox thursCheckBox = (CheckBox) findViewById(R.id.ThursCheckBox);
            CheckBox friCheckBox = (CheckBox) findViewById(R.id.FridayCheckBox);
            CheckBox satCheckBox = (CheckBox) findViewById(R.id.SaturdayCheckBox);
            CheckBox sunCheckBox = (CheckBox) findViewById(R.id.SundayCheckBox);

            if (monCheckBox.isChecked())
            {
                daysOfWeek.add("Monday");
            }
            if (tuesCheckBox.isChecked())
            {
                daysOfWeek.add("Tuesday");
            }
            if (wedCheckBox.isChecked())
            {
                daysOfWeek.add("Wednesday");
            }
            if (thursCheckBox.isChecked())
            {
                daysOfWeek.add("Thursday");
            }
            if (friCheckBox.isChecked())
            {
                daysOfWeek.add("Friday");
            }
            if (satCheckBox.isChecked())
            {
                daysOfWeek.add("Saturday");
            }
            if (sunCheckBox.isChecked())
            {
                daysOfWeek.add("Sunday");
            }
            return daysOfWeek;
        }



}
