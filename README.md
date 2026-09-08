# Log Parser & Anomaly Detector

I built this to get hands-on with Java file I/O, regex, and collections. It reads an authentication log file line by line, parses each entry with regex, and checks for suspicious login activity: repeated failures from the same IP, unknown usernames, brute-force attempts, and logins at odd hours.

## Files

- `LogEntry.java` — holds the data for one parsed log line
- `LogParser.java` — reads the log file and pulls out fields with regex
- `AlertRule.java` — the actual detection logic (failed logins, unknown users, brute-force, unusual hours)
- `Report.java` — ties it all together and prints the summary
- `Main.java` — entry point
- `data/sample.log` — a sample log file I put together for testing, with some attack patterns baked in on purpose

## How to run it

1. Get the files onto your desktop — either:
   - Clone the repo:
     ​```
     git clone https://github.com/alielsakka971-glitch/Log-parser-anomaly-detector.git
     ​```
   - Or click the green **Code** button on GitHub → **Download ZIP**, then unzip it

2. Open a terminal in the project folder, then compile:
   ​```
   cd src
   javac *.java
   ​```

3. Run it:
   ​```
   java Main
   ​```

It reads `data/sample.log` and prints the report straight to the console.

## What I practiced

- **File I/O** — used `BufferedReader`/`FileReader` to read the log line by line
- **Regex** — used `Pattern`/`Matcher` to pull the timestamp, event type, user, and IP out of each line
- **Collections** — used `ArrayList`, `HashMap`, and `HashSet` to store and group the parsed data
