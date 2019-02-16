package designchallenge1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class DayChecker
{
    ArrayList <CalendarEvent> events;


    public DayChecker(ArrayList<CalendarEvent> events)
    {
        this.events = events;
        checkEvents();
    }

    public void checkEvents ()
    {
        ArrayList<CalendarEvent> currentEvents = new ArrayList<>();

        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        int day = LocalDate.now().getDayOfMonth();
        System.out.println("Today is: "+month+"/"+day+"/"+year);
        for (CalendarEvent e: events)
            if (e.getMonth()+1==month&&e.getDay()==day&&e.getYear()==year)
                currentEvents.add(e);

        SubjectInterface mediaNotifier = new MediaNotifier(currentEvents);

        mediaNotifier.register (new FbController());
        mediaNotifier.register (new SmsController());
        if (currentEvents.size()>0)
        {
            mediaNotifier.notifyObserver();
        }



    }
}
