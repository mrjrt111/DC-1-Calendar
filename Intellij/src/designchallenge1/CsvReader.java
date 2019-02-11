package designchallenge1;

import java.io.*;

public class CsvReader extends ParserAbstract {

    private String filename;
    public CsvReader(String filename) throws Exception {
        this.filename = filename;
        loadFile();
        readFile();

    }

    @Override
    void loadFile() throws Exception {
        FileReader fr = new FileReader(this.filename);
        BufferedReader br = new BufferedReader(fr);

        while((br.read())!=-1){
            System.out.println(br.readLine());
        }
        br.close();
        fr.close();

    }

    @Override
    void readFile() {

    }
}
