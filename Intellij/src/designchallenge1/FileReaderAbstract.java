package designchallenge1;

import java.io.IOException;
import java.util.ArrayList;

//Abstract Class used to handle parser classes that will be used to read dates
public abstract class FileReaderAbstract {

    ArrayList<String> content; //used to hold content read from file

    public abstract void loadFile () throws  Exception; //used to load file, filename is the path

    public abstract void readFile () throws IOException;//used to read file

    public ArrayList<String> getContent()
    {
        return content;
    }

}
