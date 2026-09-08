import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class AlertRule{

public static List<String> failed(List<LogEntry> list) {
    HashMap<String, List<String>> failuresByIp = new HashMap<>();
    List<String> alerts = new ArrayList<>();

    for (LogEntry entry : list) {
        if (entry.getEventType().equals("FAILED_LOGIN")) {
            String ip = entry.getIp();
            if (!failuresByIp.containsKey(ip)) {
                failuresByIp.put(ip, new ArrayList<>());
            }
            failuresByIp.get(ip).add(entry.getTimestamp());
        }
    }

    for (String ip : failuresByIp.keySet()) {
        List<String> timestamps = failuresByIp.get(ip);
        if (timestamps.size() >= 5) {
            String message = ip + " flagged: " + timestamps.size() + " failed logins at " + timestamps;
            alerts.add(message);
        }
    }

    return alerts;
}
public static List<String> unknownUsers(List<LogEntry> list) {
    Set<String> knownUsers = new HashSet<>();
    knownUsers.add("malak");
    knownUsers.add("omar");
    knownUsers.add("sara");
    knownUsers.add("youssef");
    knownUsers.add("nour");
    knownUsers.add("hana");

    HashMap<String, Integer> counts = new HashMap<>();
    List<String> alerts = new ArrayList<>();

    for (LogEntry entry : list) {
        String name = entry.getName();
        if (!knownUsers.contains(name)) {
            if (counts.containsKey(name)) {
                counts.put(name, counts.get(name) + 1);
            } else {
                counts.put(name, 1);
            }
        }
    }

    for (String name : counts.keySet()) {
        int count = counts.get(name);
        alerts.add(name + ": " + count + " attempts (unknown user)");
    }

    return alerts;
}
public static List<String> failedByUser(List<LogEntry> list) {
    HashMap<String, Integer> counts = new HashMap<>();
    List<String> alerts = new ArrayList<>();

    for (LogEntry entry : list) {
        if (entry.getEventType().equals("FAILED_LOGIN")) {
            String name = entry.getName();
            if (counts.containsKey(name)) {
                counts.put(name, counts.get(name) + 1);
            } else {
                counts.put(name, 1);
            }
        }
    }

    for (String name : counts.keySet()) {
        int count = counts.get(name);
        if (count >= 5) {
            alerts.add(name + " flagged: " + count + " failed logins (possible brute-force across multiple IPs)");
        }
    }

    return alerts;
}
public static List<String> unusualHours(List<LogEntry> list) {
    List<String> alerts = new ArrayList<>();

    for (LogEntry entry : list) {
        String timestamp = entry.getTimestamp();
        String hourStr = timestamp.substring(11, 13);
        int hour = Integer.parseInt(hourStr);

        if (hour < 6 || hour >= 22) {
            alerts.add(entry.getName() + " logged in at unusual hour: " + timestamp);
        }
    }

    return alerts;
}
}