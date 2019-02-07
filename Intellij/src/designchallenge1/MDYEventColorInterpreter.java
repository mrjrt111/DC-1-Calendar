package designchallenge1;
import java.util.ArrayList;

//Handles content reading in the format: Month/Day/Year, Event, Color
public class MDYEventColorInterpreter implements DataInterpreterAdapter {

    private ArrayList<String> content;

    public MDYEventColorInterpreter(ArrayList<String> content)
    {
        this.content = content;
    }



/*
    @Override
    public int [][] getDates() //Stores Date in a 2D Array: [n][0] Month, [n][1] Day, [n][2] Year
    {
        int [][] dates = new int [content.size()/3][3];
        String [] temp;
        int j = 0;

        for (int i = 0; i<content.size(); i+=3)
        {

            temp = content.get(i).split("/");

            dates[j][0] = Integer.valueOf(temp[0]);
            dates[j][1] = Integer.valueOf(temp[1]);
            dates[j][2] = Integer.valueOf(temp[2]);
            j++;
        }

        return dates;
    }

    @Override
    public String[] getEvents()
    {
        int i = 0;
        String [] events = new String[content.size()/3];
        for (int j = 1; j<content.size(); j+=3) {
            events[i] = content.get(j);
            i++;
        }
        return events;
    }

    @Override
    public String[] getColors() {
        int i = 0;
        String [] colors = new String[content.size()/3];
        for (int j = 2; j<content.size(); j+=3) {
            colors[i] = content.get(j);
            i++;
        }
        return colors;
    }
*/
    @Override
    public ArrayList<CalendarEvent> dataToCalendarEvents()
    {
        String [] temp;

        ArrayList <CalendarEvent> events = new ArrayList<>();
        for (int i = 0; i<content.size(); i+=3)
        {

            temp = content.get(i).split("/");

            events.add(new CalendarEvent(Integer.valueOf(temp[0]),Integer.valueOf(temp[1]), Integer.valueOf(temp[2]),
                    content.get(i+1),content.get(i+2)));

        }

        return events;
    }
}
