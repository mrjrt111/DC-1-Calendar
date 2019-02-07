package designchallenge1;

public class tester //Used to test classes not needed in the program
{
    public static void main (String [] args) throws Exception {
        CsvReader reader = new CsvReader("C:\\Users\\jarrett\\Documents\\DLSU\\AY 2018 - 2019\\Term 2\\SWDESPA\\MP\\DC 1\\Git Version\\DC-1-Calendar\\Intellij\\Sample Files\\Philippine Holidays.csv");

       /* for (String word: reader.getContent()) //used to test the arraylist's content
            System.out.println(word);
            */
       MDYEventColorInterpreter interpreter = new MDYEventColorInterpreter(reader.getContent());

       /* for (CalendarEvent e:interpreter.dataToCalendarEvents()) //used to test event content
        {
            System.out.println("Month " + e.getMonth());
            System.out.println("Day " + e.getDay());
            System.out.println("Year " + e.getYear());
            System.out.println("Event " + e.getHoliday());
            System.out.println("Color " + e.getColor());
            System.out.println();
        }
        */
    }
}
