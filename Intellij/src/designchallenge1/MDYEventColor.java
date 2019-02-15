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
        String [] dateTemp;
        boolean isYearly;

        ArrayList <CalendarEvent> events = new ArrayList<>();
        for (int i = 0; i<content.size(); i+=4)
        {
           // System.out.println(content.get(i));
            dateTemp = content.get(i).split("/");

            if (content.get(i+3).toLowerCase().equals("yes"))
                isYearly = true;
            else
                isYearly = false;



            events.add(new CalendarEvent(Integer.valueOf(dateTemp[0])-1,Integer.valueOf(dateTemp[1]), Integer.valueOf(dateTemp[2]),
                    content.get(i+1),new Color (Integer.valueOf(content.get(i+2))), isYearly));

        }

        return events;
    }
}
