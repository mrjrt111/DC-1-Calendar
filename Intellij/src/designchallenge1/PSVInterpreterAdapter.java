package designchallenge1;

import java.util.ArrayList;

public class PSVInterpreterAdapter implements DataInterpreterInterface{

    EventMDYColor interpreter;
    public PSVInterpreterAdapter(ArrayList<String> content)
    {
        this.interpreter = new EventMDYColor(content);
    }
    @Override
    public ArrayList<CalendarEvent> dataToCalendarEvents()
    {
        return interpreter.toEventMDYColor();
    }
}
