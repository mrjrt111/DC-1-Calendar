package designchallenge1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

//Abstract Class used to handle parser classes that will be used to read dates
public abstract class ParserAbstract {

    ArrayList<String> content; //used to hold content read from file

    abstract void loadFile () throws  Exception; //used to load file, filename is the path

    abstract void readFile ();//used to read file

}