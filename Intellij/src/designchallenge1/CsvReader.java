package designchallenge1;
import java.io.*;
import java.util.ArrayList;


//CsvReader is used to parse csv files
public class CsvReader extends ParserAbstract
{

    private String filename; //filename to be used
    private FileReader fReader; //used to read the file
    private BufferedReader bReader;// used to read the content of the file

    private int size; //size of entries per line

    public CsvReader(String filename) throws Exception
    {
        this.filename = filename;
        loadFile();
        readFile();
    }

    @Override
    public void loadFile() throws Exception
    {
        super.content = new ArrayList<>();
        fReader = new FileReader(this.filename);
        bReader = new BufferedReader(fReader);
    }

    @Override
    public void readFile() throws IOException
    {
        int i;

        String [] fEntry = bReader.readLine().split(", "); //gets the first entry and splits it
        size = fEntry.length; //gets the number of entry in the first line
        String temp;

        for (i = 0; i<size; i++) //loop adds the first entry's content to the arraylist
            content.add(fEntry[i]);

        while((temp = bReader.readLine())!=null) //loop that adds the remaining entries
        {
            fEntry = temp.split(", ");

            for (i = 0; i<size; i++)
                content.add(fEntry[i]);
        }
        bReader.close();
        fReader.close();

      /* for (String word: content) //used to test the arraylist's content
            System.out.println(word); */
    }

    public int getSize() {
        return size;
    }

}
