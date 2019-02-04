package designchallenge1;

//Adapter Pattern for Interpreter with different date format,positioning
public interface DataInterpreterAdapter
{
    public int [][]getDates();
    public String [] getEvents();
    public String [] getColors();
}
