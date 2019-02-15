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
        {
            if (e.getisYearly())
            {
                if (e.getYear()<=year&&e.getMonth()==month)
                    sortedEvents.add(e);
            }
            else
                if (e.getYear()==year&&e.getMonth()==month)
                    sortedEvents.add(e);
        }

        System.out.println("sortedEvents: "+sortedEvents.size());

        for (int i = 0; i<sortedEvents.size()-1; i++)
        {
         //   System.out.println("loop I: " + i);
            for (int j = 0; j<sortedEvents.size()-i-1; j++)
            {
           //     System.out.println("loop J: " + j);
               // System.out.println(sortedEvents.get(j).getMonth()+ "/"+
                      //  sortedEvents.get(j).getDay()+"/"+ sortedEvents.get(j).getYear());
                //System.out.println(sortedEvents.get(j+1).getMonth()+ "/"+
                      //  sortedEvents.get(j+1).getDay()+"/"+ sortedEvents.get(j+1).getYear());
                //System.out.println(sortedEvents.get(j).getDay() + "    " + sortedEvents.get(j+1).getDay());
                if (sortedEvents.get(j).getDay()>=sortedEvents.get(j+1).getDay())
                {
                    CalendarEvent temp = sortedEvents.get(j);
                    sortedEvents.set(j, sortedEvents.get(j+1));
                    sortedEvents.set(j+1, temp);
                }
            }
        }

       // System.out.println("size:" +sortedEvents.size());
        return sortedEvents;
    }

}
