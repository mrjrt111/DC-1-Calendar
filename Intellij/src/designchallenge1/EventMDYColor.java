package designchallenge1;

import java.awt.*;
import java.util.ArrayList;

public class EventMDYColor {

    private ArrayList<String> content;

    public EventMDYColor(ArrayList<String> content) { this.content = content; }

    public ArrayList<CalendarEvent> toEventMDYColor() {
        String [] temp;

        ArrayList <CalendarEvent> events = new ArrayList<>();
        for (int i = 0; i<content.size(); i+=3)
        {

            temp = content.get(i+1).split("/");

            events.add(new CalendarEvent(Integer.valueOf(temp[0]),Integer.valueOf(temp[1]), Integer.valueOf(temp[2]),
                    content.get(i),new Color(Integer.valueOf(content.get(i+2)))));
        }
        return events;
    }
}