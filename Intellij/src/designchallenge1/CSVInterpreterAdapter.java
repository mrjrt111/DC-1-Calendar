package designchallenge1;

import java.util.ArrayList;

/*
    This class acts an Adapter to the CSV interpreter in the calendar program
*/
public class CSVInterpreterAdapter implements DataInterpreterInterface
{
    private MDYEventColor interpreter;

    /*
        This constructor accepts the interface used here.
    */
    public CSVInterpreterAdapter(ArrayList<String> content) {
        this.interpreter = new MDYEventColor(content);
    }

    /*
        This method adapts the method used in the toMDYEventColor class.
     */
    @Override
    public ArrayList<CalendarEvent> dataToCalendarEvents()
    {
        return interpreter.toMDYEventColor();
    }
}   
