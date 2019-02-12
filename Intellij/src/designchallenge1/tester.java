package designchallenge1;

import facebook.FBView;

import java.awt.*;
import java.util.ArrayList;

public class tester //Used to test classes not needed in the program
{
    public static void main (String [] args) throws Exception {
       /*
        CsvReader reader = new CsvReader("C:\\Users\\jarrett\\Documents\\DLSU\\AY 2018 - 2019\\Term 2\\SWDESPA\\MP\\DC 1" +
                "\\Git Version\\DC-1-Calendar\\Intellij\\Sample Files\\Philippine Holidays.csv");
       /* for (String word: reader.getContent()) //used to test the arraylist's content
            System.out.println(word);
            */
      // MDYEventColor interpreter = new MDYEventColor(reader.getContent());

      /*  for (CalendarEvent e:interpreter.toMDYEventColor()) //used to test event content
        {
            System.out.println("Month " + e.getMonth());
            System.out.println("Day " + e.getDay());
            System.out.println("Year " + e.getYear());
            System.out.println("Event " + e.getHoliday());
            System.out.println("Color " + e.getColor());
            System.out.println();
        }


       MDYEventColorCsvWriter writer = new MDYEventColorCsvWriter("test.csv", interpreter.toMDYEventColor());
       writer.saveData();
       */

      /*psvReader psvreader = new psvReader("C:\\Users\\jarrett\\Documents\\DLSU\\AY 2018 - 2019\\Term 2\\" +
              "SWDESPA\\MP\\DC 1\\Git Version\\DC-1-Calendar\\Intellij\\Sample Files\\DLSU Unicalendar.psv");

      EventMDYColor interpreter = new EventMDYColor(psvreader.getContent());

        for (CalendarEvent e:interpreter.toMDYEventColor()) //used to test event content
        {
            System.out.println("Month " + e.getMonth());
            System.out.println("Day " + e.getDay());
            System.out.println("Year " + e.getYear());
            System.out.println("Event " + e.getHoliday());
            System.out.println("Color " + e.getColor());
            System.out.println();
        }
*/
        ArrayList<CalendarEvent> e = new ArrayList<>();
        e.add(new CalendarEvent(2, 12, 2019, "poop", new Color(0)));
      SmsController controller = new SmsController();
      controller.update(e);

    }
}
