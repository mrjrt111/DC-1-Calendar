package designchallenge1;
import java.awt.*;
import java.util.ArrayList;

//Handles content reading in the format: Month/Day/Year, Event, Color
public class MDYEventColor {

    private ArrayList<String> content;

    public MDYEventColor(ArrayList<String> content)
    {
        this.content = content;
    }


    public ArrayList<CalendarEvent> toMDYEventColor()
    {
        String [] temp;

        ArrayList <CalendarEvent> events = new ArrayList<>();
        for (int i = 0; i<content.size(); i+=3)
        {
            System.out.println(content.get(i));
            temp = content.get(i).split("/");

            events.add(new CalendarEvent(Integer.valueOf(temp[0])-1,Integer.valueOf(temp[1]), Integer.valueOf(temp[2]),
                    content.get(i+1),new Color (Integer.valueOf(content.get(i+2)))));

        }

        return events;
    }
}
