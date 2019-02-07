package designchallenge1;

import java.io.IOException;
import java.util.ArrayList;
//This abstract class is used in template design to export a saved data file
public abstract class DataSavingAbstract
{
    ArrayList<CalendarEvent> content;

    public abstract void saveData () throws IOException;
}
