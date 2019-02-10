package designchallenge1;

import java.util.ArrayList;

public class CSVInterpreterAdapter implements DataInterpreterInterface
{
    private MDYEventColor interpreter;

    public CSVInterpreterAdapter(ArrayList<String> content) {
        this.interpreter = new MDYEventColor(content);
    }

    @Override
    public ArrayList<CalendarEvent> dataToCalendarEvents()
    {
        return interpreter.toMDYEventColor();
    }
}   
