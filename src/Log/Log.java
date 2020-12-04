package Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;

public class Log {

    private FileWriter fstream;
    BufferedWriter out = null;

    public void open() {
        try {
            fstream = new FileWriter("log.txt", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            DateFormat date = DateFormat.getDateTimeInstance();
            out.write("[" + date.getCalendar().getTime() + "]\n");
            //  out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLog(String message) {
        try {
            out.write(message);
        } catch (Exception e) {
            System.out.println("unknown");
        }

    }

    public void close(){
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
