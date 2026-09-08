public class LogEntry{ 
    String timestamp;
    String eventType;
    String name;
    String ip;
    public LogEntry(String ts, String ET, String N, String IP){
    timestamp=ts;
    eventType=ET;
    name=N;
    ip=IP;
    }
    public String toString() {
    return timestamp + " | " + eventType + " | user=" + name + " | ip=" + ip;
}
public String getName() {
    return name;
}
public String getTimestamp() {
    return timestamp;
}
public String getEventType() {
    return eventType;
}
public String getIp() {
    return ip;
}

}