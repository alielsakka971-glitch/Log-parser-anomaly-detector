import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    public static LogEntry parseLine(String line) {
        String timestamp = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}) ";
        String eventType = "([A-Z_]+)";
        String user = " user=([\\w]+)";
        String ip = " ip=([\\d.]+)";
        String regex = timestamp + eventType + user + ip;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new LogEntry(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        } else {
            return null;
        }
    }

    public static List<LogEntry> parse(String path) {
        List<LogEntry> logEntries = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                LogEntry entry = parseLine(line);
                if (entry != null) {
                    logEntries.add(entry);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }
        return logEntries;
    }
}