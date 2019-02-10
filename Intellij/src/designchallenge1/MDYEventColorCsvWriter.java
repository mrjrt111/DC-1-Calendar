package designchallenge1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MDYEventColorCsvWriter extends DataSavingAbstract {


    BufferedWriter bufferedWriter;
    File file;
    FileWriter fileWriter;
    ArrayList<CalendarEvent> content;
    public MDYEventColorCsvWriter(String filename, ArrayList <CalendarEvent> content)
    {
        bufferedWriter = null;
        this.file = new File (filename);
        this.content = content;
    }


    @Override
    public void saveData() throws IOException
    {
        try
        {
            if (!file.exists())
                file.createNewFile();

            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i<content.size(); i++)
            {
                bufferedWriter.write(content.get(i).getMonth() + "/"+ content.get(i).getDay()+ "/"+content.get(i).getYear()+", "+
                        content.get(i).getHoliday() + ", "+content.get(i).getColor().getRGB()+ "\n");
            }
        }
        catch (IOException ioe){ioe.printStackTrace();}
        finally
        {
            try
            {
                if (bufferedWriter!=null)
                    bufferedWriter.close();
            }catch (Exception e)
            {
                System.out.println("Error in closing ");
            }
        }
    }


}
