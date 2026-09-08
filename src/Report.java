import java.util.List;

public class Report {
    public static void generate(String path) {
        List<LogEntry> entries = LogParser.parse(path);
        if (entries == null) {
            System.out.println("Could not read log file.");
            return;
        }

        List<String> failedAlerts = AlertRule.failed(entries);
        List<String> unknownAlerts = AlertRule.unknownUsers(entries);
        List<String> bruteForceAlerts = AlertRule.failedByUser(entries);
        List<String> unusualHourAlerts = AlertRule.unusualHours(entries);

        System.out.println("      LOG PARSER REPORT      ");
        System.out.println("Total entries processed: " + entries.size());
        System.out.println("Failed-login alerts: " + failedAlerts.size());
        System.out.println("Unknown-user alerts: " + unknownAlerts.size());
        System.out.println("Brute-force-by-user alerts: " + bruteForceAlerts.size());
        System.out.println("Unusual-hour alerts: " + unusualHourAlerts.size());
        System.out.println();

        System.out.println("    Failed Login Alerts    ");
        for (String alert : failedAlerts) {
            System.out.println(alert);
        }

        System.out.println();
        System.out.println("     Unknown User Alerts     ");
        for (String alert : unknownAlerts) {
            System.out.println(alert);
        }
        System.out.println();
System.out.println("     Brute force by user Alerts      ");
for (String alert : bruteForceAlerts) {
    System.out.println(alert);
}
System.out.println();
System.out.println("    Unusual Login Hours Alert    ");
for (String alert : unusualHourAlerts) {
    System.out.println(alert);
}
    }
}