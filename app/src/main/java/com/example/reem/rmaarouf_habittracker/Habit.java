package com.example.reem.rmaarouf_habittracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Reem on 2016-09-29.
 */
public class Habit implements Serializable{

    private Date date;
    private String name;
    private ArrayList<String> daysOfWeek;
    private int numberOfCompletions;

    // Code taken and adjusted from http://www.javapractices.com/topic/TopicAction.do?Id=56 Sept 24, 2016
    private UUID uniqueID;
    private ArrayList<Date> completedDates;
    private boolean complete;


    /*
    Habit class is an object for the HabitTracker. Contains contents such as name, completions, daysOfWeek and date.
     */
    public Habit(Date date, String name, ArrayList<String> daysOfWeek)
    {
        this.daysOfWeek = daysOfWeek;
        this.date = date;
        this.name = name;
        this.numberOfCompletions = 0;

        // Code taken and adjusted from http://www.javapractices.com/topic/TopicAction.do?Id=56 Sept 24, 2016
        this.uniqueID = UUID.randomUUID();

        this.completedDates = new ArrayList<Date>();
        this.complete=false;
    }

    public Habit(){};



    public void setComplete(boolean value)
    {
        this.complete=value;
    }

    public boolean getComplete()
    {
        return complete;
    }

    @Override
    public String toString( )
    {
        if (complete==false)
        {
            return name;
        }
        return name+" - completed on "+date.toString();
    }

    public void addCompletedDate(Date date)
    {
        completedDates.add(date);
    }

    public String getUniqueID()
    {
        return uniqueID.toString();
    }


    public void removeHabitCompletion(Habit habit)
    {
        this.completedDates.remove(habit.getDate());
    }


    public boolean equals(Habit habit)
    {
        if (this.getUniqueID().equals(habit.getUniqueID()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDaysOfWeek(ArrayList<String> daysOfWeek)
    {
        this.daysOfWeek = daysOfWeek;
    }

    public Date getDate()
    {
        return date;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<String> getDaysOfWeek()
    {
        return daysOfWeek;
    }

    public int getNumberOfCompletions() {
        return numberOfCompletions;
    }

    public void setNumberOfCompletions(int numberOfCompletions) {
        this.numberOfCompletions = numberOfCompletions;
    }

    public ArrayList<Date> getCompletedDates() {
        return completedDates;
    }

    public void setCompletedDates(ArrayList<Date> completedDates) {
        this.completedDates = completedDates;
    }
}
