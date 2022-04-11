package tests;

import org.junit.jupiter.api.Test;

import discreteBehaviorSimulator.LogFormatter;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.*;



public class LogFormatterTest {

    private LogFormatter logForm = new LogFormatter();
    
    @Test
    public void LFf1() {

        LogRecord logRec    = new LogRecord(Level.WARNING, "msg");
        String formatted = this.logForm.format(logRec);

        String dateFormatted = formatted.split(": ")[0];
        
        assertTrue(dateFormatted instanceof String);
    }



}