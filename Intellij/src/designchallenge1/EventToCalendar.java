package designchallenge1;

import java.util.ArrayList;

public class EventToCalendar
{
    ArrayList<CalendarEvent> events;


    public EventToCalendar(ArrayList<CalendarEvent> events)
    {
        this.events = events;
    }

    public ArrayList <CalendarEvent> eventsInMonth (int month, int year)
    {
        ArrayList <CalendarEvent> sortedEvents = new ArrayList<>();
        for (CalendarEvent e: events)
            if (e.getYear()==year)
                sortedEvents.add(e);


        for (int i = 0; i<sortedEvents.size()-1; i++)
            for (int j = 0; j<sortedEvents.size()-i; j++)
                if (sortedEvents.get(j).getDay()>sortedEvents.get(j+1).getDay())
                {
                    CalendarEvent temp = sortedEvents.get(j);
                    sortedEvents.set(j, sortedEvents.get(j+1));
                    sortedEvents.set(j+1, sortedEvents.get(j));
                }



        return sortedEvents;
    }
}
