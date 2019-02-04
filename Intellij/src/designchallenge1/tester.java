package designchallenge1;

public class tester //Used to test classes not needed in the program
{
    public static void main (String [] args) throws Exception {
        CsvReader reader = new CsvReader("C:\\Users\\jarrett\\Documents\\DLSU\\AY 2018 - 2019\\Term 2\\SWDESPA\\MP\\DC 1\\Git Version\\DC-1-Calendar\\Intellij\\Sample Files\\Philippine Holidays.csv");

       /* for (String word: reader.getContent()) //used to test the arraylist's content
            System.out.println(word);
            */
        MDYEventColorInterpreter interpreter = new MDYEventColorInterpreter(reader.getContent());

        /*interpreter.storeDate(); //used to test date content
        for (int i = 0; i<interpreter.storeDate().length; i++)
            for (int j =  0; j<3; j++)
                System.out.println("Array"+i+" "+j +" " + interpreter.storeDate()[i][j]);
            */

     /*for (String event:  interpreter.storeEvent()) //used to test event content
           System.out.println(event);
           */

      /*  for (String color:  interpreter.storeColor())
            System.out.println(color); */
    }
}
