package designchallenge1;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;

public class EventsHandler
{
    ArrayList<CalendarEvent> loadedEvents;

    public ArrayList<CalendarEvent> getCreatedEvents() {
        return createdEvents;
    }

    ArrayList<CalendarEvent> createdEvents;
    ArrayList<CalendarEvent> totalEvents;

    DayChecker dayChecker;

    /***
     * EventHandler handles majority of the of the features that involve Calendar Event
     *
     * */
    public EventsHandler()
    {
        try {
            ParserAbstract csvReader = new CsvReader("Sample Files/UserEvent.csv");
            ParserAbstract psvReader = new psvReader("Sample Files/Holiday.psv");


            InterpreterAdapter adapter = new InterpreterAdapter(((psvReader)psvReader).getContent());
            this.loadedEvents = adapter.dataToCalendarEvents();
            InterpreterAdapter adapter2 = new InterpreterAdapter(((CsvReader)csvReader).getContent());
            this.createdEvents = adapter2.dataToCalendarEvents();

            totalEvents = new ArrayList<>();
            this.totalEvents.addAll(this.loadedEvents);
            this.totalEvents.addAll(this.createdEvents);

            System.out.println("load: "+ loadedEvents.size()+"\ncreate: "+createdEvents.size()+"\nevents: "+totalEvents.size());

            //loadedEvents.addAll(adapter.dataToCalendarEvents());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Files to be loaded not found");
        }

    }

    public void addEvent (CalendarEvent event)
    {
        this.createdEvents.add(event);
        this.totalEvents.add(event);
    }

    public void editEventInfo (CalendarEvent oldEvent, CalendarEvent newEvent)
    {
        int createdIndex =this.createdEvents.indexOf(oldEvent);
        int totalIndex = this.totalEvents.indexOf(oldEvent);

        this.createdEvents.set(createdIndex, newEvent);
        this.createdEvents.set(totalIndex, newEvent);

    }

    public void deleteEvent (CalendarEvent event)
    {
        createdEvents.remove(event);
        totalEvents.remove(event);
        System.out.println("In Event Handler, deleted: "+ event.getHoliday());
    }

    public ArrayList <CalendarEvent> getEventsThisMonth (int month, int year)
    {
        ArrayList <CalendarEvent> sortedEvents = new ArrayList<>();
        for (CalendarEvent e: totalEvents)
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

        for (int i = 0; i<sortedEvents.size()-1; i++)
        {
            //   System.out.println("loop I: " + i);
            for (int j = 0; j<sortedEvents.size()-i-1; j++)
            {

                if (sortedEvents.get(j).getDay()>=sortedEvents.get(j+1).getDay())
                {
                    CalendarEvent temp = sortedEvents.get(j);
                    sortedEvents.set(j, sortedEvents.get(j+1));
                    sortedEvents.set(j+1, temp);
                }
            }
        }

        return sortedEvents;
    }


    public void notifPopup ()
    {
        dayChecker = new DayChecker (totalEvents);
    }
}
