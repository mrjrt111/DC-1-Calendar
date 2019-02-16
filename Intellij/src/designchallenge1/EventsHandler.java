package designchallenge1;

import java.util.ArrayList;

public class EventsHandler
{
   private ArrayList<CalendarEvent> loadedEvents;
   private ArrayList<CalendarEvent> createdEvents;
   private ArrayList<CalendarEvent> totalEvents;

    /***
     * EventHandler handles majority of the of the features that involve Calendar Event
     *
     * */
    public EventsHandler()
    {
        try {
            FileReaderAbstract csvReader = new CsvReader("C:\\Users\\user\\Desktop\\DC-1-Calendar\\Intellij\\Sample Files\\UserEvent.csv");
            FileReaderAbstract psvReader = new psvReader("C:\\Users\\user\\Desktop\\DC-1-Calendar\\Intellij\\Sample Files\\Holiday.psv");


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


    public void deleteEvent (CalendarEvent event)
    {
        createdEvents.remove(event);
        totalEvents.remove(event);
        System.out.println("In Event Handler, deleted: "+ event.getSchedEvent());
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


    public void notificationCaller()
    {
        DayChecker dayChecker;
        dayChecker = new DayChecker (totalEvents);
    }
    public ArrayList<CalendarEvent> getCreatedEvents() {
        return createdEvents;
    }
}
