package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class Logger {
    public static synchronized void log(Level level, String value){

        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        System.out.println(Thread.currentThread().getName()+" "+time+ "_" +level.getName() + "> " +value);
    }
}
