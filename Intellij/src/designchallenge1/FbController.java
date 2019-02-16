package designchallenge1;

import facebook.FBView;

import javax.swing.*;
import java.util.ArrayList;

public class FbController implements ObserverInterface
{


    @Override
    public void update(ArrayList<CalendarEvent> event)
    {
        FBView view;
        view = new FBView();
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.setTitle("Facebook Feed");
        for (CalendarEvent e:event)
        view.showNewEvent(e.getSchedEvent(), e.getMonth() + 1, e.getDay(), e.getYear(), e.getColor());
    }
}
