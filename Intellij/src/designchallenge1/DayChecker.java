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
            ArrayList <ObserverInterface> observers = new ArrayList<>();
            observers.add (new FbController());
            observers.add(new SmsController());
            if (currentEvents.size()>0)
            {
                MediaNotifier mediaNotifier = new MediaNotifier(currentEvents, observers);
                mediaNotifier.notifyObserver();
            }



    }
}
