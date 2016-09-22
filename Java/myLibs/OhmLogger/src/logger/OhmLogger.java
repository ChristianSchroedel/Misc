/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author christian
 */
public final class OhmLogger
{
    private static FileHandler fileTxt;
    private static ConsoleHandler ch;
    
    private final static Logger logger = Logger.getLogger("");
    
    /**
     * Setup logger class
     */
    public static void setup()
    {
        Handler[] handlers = logger.getHandlers();
        
        for (Handler handler : handlers)
            logger.removeHandler(handler);
        
        logger.setUseParentHandlers(false);
        
        Properties prop = new Properties();
        InputStream is = OhmLogger.class.getResourceAsStream("logger.properties");
        
        try
        {
            prop.load(is);
            String logLevel = prop.getProperty("LOG_LEVEL");
            String outputFile = prop.getProperty("LOG_FILE");

            Level lvl = Level.parse(logLevel);
            logger.setLevel(lvl);

            ch = new ConsoleHandler();
            ch.setFormatter(new MyFormatter());
            ch.setLevel(lvl);
            logger.addHandler(ch);
            
            fileTxt = new FileHandler(outputFile, true);
            fileTxt.setFormatter(new MyFormatter());
            fileTxt.setLevel(lvl);
            logger.addHandler(fileTxt);
        } 
        catch (IOException ex)
        {
            System.err.println(ex);
        }
        catch (SecurityException ex)
        {
            System.err.println(ex);
        }
    }
  
    /**
     * Create custom formatter
     */
    static class MyFormatter extends SimpleFormatter
    {
        @Override
        public String format(LogRecord rec)
        {
            StringBuilder sb = new StringBuilder();
            Date date = Calendar.getInstance().getTime();
            sb.append("### ");
            sb.append(date.toString());
            sb.append(" # lvl:");
            sb.append(String.format("%-7s", rec.getLevel()));
            sb.append(" # tID:");
            sb.append(String.format("%05d", rec.getThreadID()));
            
            if( rec.getMessage() != null )
            {
                sb.append(" # '");
                sb.append(rec.getMessage());
                sb.append("'");
            }
            
            if( rec.getThrown() != null )
            {
                sb.append("\n");
                sb.append("   ");
                sb.append(rec.getThrown());
                sb.append("\n");
            }
            
            sb.append("\n");

            return sb.toString();
        }
    }
    
//    public static Logger getLogger()
//    {
//        if (instance == null)
//        {
//            instance = Logger.getLogger();
//            instance.setup();
//        }
//        return instance;
//    }
//    private static Logger lg = OhmLogger.getLogger();
//    
    public static void log(Level lvl, String msg)
    {
        logger.log(lvl, msg);
    }
    
    public static void log(Level lvl, String msg, Exception ex)
    {
        logger.log(lvl, msg, ex);
    }

    public static void finest(String msg)
    {
        logger.finest(msg);
    }
    
    public static void finer(String msg)
    {
        logger.finer(msg);
    }
    
    public static void fine(String msg)
    {
        logger.fine(msg);
    }
    
    public static void info(String msg)
    {
        logger.info(msg);
    }
    
    public static void warning(String msg)
    {
        logger.warning(msg);
    }
    
    public static void severe(String msg)
    {
        logger.severe(msg);
    }
}
 
