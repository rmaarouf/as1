package com.example.reem.rmaarouf_habittracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Reem on 2016-09-29.
 */

/*
Contains the list of habits for the habitTracker
 */
public class HabitList {

    // Knowledge gained from Thursday Lab Sept 29, 2016

    private ArrayList<Habit> habits;

    public HabitList()
    {
        habits = new ArrayList<Habit>();
    }
    public ArrayList<Habit> getHabits() {
        return habits;
    }

    public void setHabits(ArrayList<Habit> habits) {
        this.habits = habits;
    }

    public void add(Habit habit)
    {
        habits.add(habit);
    }

    public void delete(Habit habit)
    {

        for (Habit thisHabit: habits)
        {
            if (thisHabit.getUniqueID().equals(habit.getUniqueID()))
            {
                habits.remove(thisHabit);
            }
        }
    }

    public void complete(Habit habit)
    {
        for (Habit thisHabit: habits)
        {
            if (thisHabit.getUniqueID().equals(habit.getUniqueID()))
            {
                thisHabit.setNumberOfCompletions(1+thisHabit.getNumberOfCompletions());
                thisHabit.addCompletedDate(new Date());
            }
        }
    }

    public void deleteCompletion(Habit habit)
    {
        for (Habit thisHabit: habits)
        {
            if (thisHabit.getUniqueID().equals(habit.getUniqueID()))
            {
                thisHabit.setNumberOfCompletions(thisHabit.getNumberOfCompletions()-1);
                thisHabit.setComplete(false);
                thisHabit.removeHabitCompletion(habit);
            }
        }
    }

    // Code taken and adjusted from https://github.com/watts1/lonelyTwitter Sept 29, 2016
    public void clear()
    {
        habits.clear();
    }

    public int getCount()
    {
        return habits.size();
    }

    // Code taken and adjusted from https://github.com/watts1/lonelyTwitter Sept 29, 2016
    public Habit getHabit(int index)
    {
        return habits.get(index);
    }




    public HabitList getHabitsList()
    {
        Collections.sort(habits, new Comparator<Habit>() {
            public int compare(Habit habit1, Habit habit2) {
                return habit1.getDate().compareTo(habit2.getDate());
            }
        });
        return this;
    }

    public boolean hasHabit(Habit habit)
    {
        return habits.contains(habit);
    }
}
