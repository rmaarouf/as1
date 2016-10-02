package com.example.reem.rmaarouf_habittracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Reem on 2016-10-01.
 */
public class HabitUnitTest {

    @Test
    public void testAddHabit()
    {
        HabitList habits = new HabitList();
        Habit habit = new Habit();
        habits.add(habit);
        try
        {
            habits.add(habit);
        }
        catch(IllegalArgumentException e)
        {
            assertTrue(habits.hasHabit(habit));

        }
    }

    @Test
    public void testHasHabit()
    {
        HabitList habits = new HabitList();
        Habit habit = new Habit();
        habits.add(habit);
        assertTrue(habits.hasHabit(habit));
    }

    @Test
    public void testDelete()
    {
        HabitList habits = new HabitList();
        Habit habit = new Habit();
        habits.delete(habit);
        assertFalse(habits.hasHabit(habit));
    }


    @Test
    public void testGetHabit()
    {
        HabitList habits = new HabitList();
        Habit habit = new Habit();
        habits.add(habit);
        assertEquals(habit, habits.getHabit(0));
    }


    @Test
    public void testGetCount()
    {
        HabitList habits = new HabitList();
        habits.add(new Habit());
        habits.add(new Habit());
        assertEquals(2, habits.getCount());
    }


    @Test
    public void testClear()
    {
        HabitList habits = new HabitList();
        habits.add(new Habit());
        habits.add(new Habit());
        habits.clear();
        assertEquals(0, habits.getCount());
    }

    @Test
    public void testDeleteCompletion()
    {
        HabitList habits = new HabitList();
        ArrayList<Date> dates = new ArrayList<Date>();
        Date date = new Date();
        dates.add(date);

        Habit habit = new Habit(date, "Habit 1", new ArrayList<String>());
        habit.setCompletedDates(dates);
        habits.add(habit);
        habits.deleteCompletion(habit);
        assertEquals(habits.getHabit(0).getCompletedDates().size(),0);
    }

    @Test
    public void testRemoveHabitCompletion()
    {
        Date date = new Date();
        Habit habit = new Habit(date,"",new ArrayList<String>());
        ArrayList<Date> dates = new ArrayList<Date>();
        dates.add(date);
        habit.setCompletedDates(dates);
        habit.removeHabitCompletion(habit);
        assertEquals(0,habit.getCompletedDates().size());

    }



    @Test
    public void testSetComplete()
    {
        Habit habit = new Habit();
        habit.setComplete(false);
        assertFalse(habit.getComplete());

    }

    @Test
    public void testAddCompletedDate()
    {
        Date date = new Date();
        Habit habit = new Habit(date, "Habit 1", new ArrayList<String>());
        habit.addCompletedDate(date);
        assertEquals(1,habit.getCompletedDates().size());
    }


    @Test
    public void testGetCompletedDates()
    {
        Date date = new Date();
        Habit habit = new Habit(date, "Habit 1", new ArrayList<String>());
        ArrayList<Date> completedDates = new ArrayList<Date>();
        completedDates.add(date);
        habit.setCompletedDates(completedDates);
        assertEquals(completedDates, habit.getCompletedDates());

    }

    @Test
    public void testHabitEquals()
    {
        Habit habit = new Habit(new Date(),"Habit 1", new ArrayList<String>());
        Habit habit2 = new Habit(new Date(),"Habit 2", new ArrayList<String>());
        assertTrue(habit.equals(habit));
        assertFalse(habit.equals(habit2));

    }

    @Test
    public void testName()
    {
        Habit habit = new Habit(new Date(), "Habit 1", new ArrayList<String>());
        assertEquals(habit.getName(),"Habit 1");
    }

    @Test
    public void testGetDaysOfWeek()
    {
        ArrayList<String> daysOfWeek = new ArrayList<String>();
        Habit habit = new Habit(new Date(), "Habit 1", daysOfWeek);
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        habit.setDaysOfWeek(daysOfWeek);
        assertEquals(daysOfWeek, habit.getDaysOfWeek());
    }


    @Test
    public void testHabitToString()
    {
        Habit habit = new Habit(new Date(), "Habit 1", new ArrayList<String>());
        assertEquals("Habit 1", habit.toString());
    }

    

}
