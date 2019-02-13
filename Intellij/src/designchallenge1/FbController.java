package designchallenge1;

import facebook.FBView;

import java.util.ArrayList;

public class FbController implements ObserverInterface
{
    FBView view;

    @Override
    public void update(ArrayList<CalendarEvent> event)
    {
        view = new FBView();
        view.setTitle("Facebook Feed");
        for (CalendarEvent e:event)
        view.showNewEvent(e.getHoliday(), e.getMonth() + 1, e.getDay(), e.getYear(), e.getColor());
    }
}
