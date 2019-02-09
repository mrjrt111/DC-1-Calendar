package designchallenge1;

import java.util.ArrayList;

public class EventMDYColorInterpreter implements DataInterpreterAdapter {

    private ArrayList<String> content;

    public EventMDYColorInterpreter(ArrayList<String> content) { this.content = content; }

    @Override
    public ArrayList<CalendarEvent> dataToCalendarEvents() {
        String [] temp;

        ArrayList <CalendarEvent> events = new ArrayList<>();
        for (int i = 0; i<content.size(); i+=3)
        {

            temp = content.get(i+1).split("/");

            events.add(new CalendarEvent(Integer.valueOf(temp[0]),Integer.valueOf(temp[1]), Integer.valueOf(temp[2]),
                    content.get(i),content.get(i+2)));
        }
        return events;
    }
}
