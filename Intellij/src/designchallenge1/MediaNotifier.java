package designchallenge1;

import java.util.ArrayList;

public class MediaNotifier implements SubjectInterface
{
    private ArrayList<CalendarEvent> currentEvents;
    private ArrayList <ObserverInterface> observer;

    public MediaNotifier(ArrayList<CalendarEvent> currentEvents, ArrayList<ObserverInterface> observer)
    {
        this.currentEvents = currentEvents;
        this.observer = observer;
    }

    @Override
    public void register(ObserverInterface e) { observer.add(e); }

    @Override
    public void unregister(ObserverInterface e)
    {
        int index = observer.indexOf(e);
        observer.remove(index);
    }

    @Override
    public void notifyObserver()
    {
        for (ObserverInterface e: observer)
            e.update(currentEvents);

    }
}
