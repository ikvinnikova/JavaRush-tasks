package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
    LinkedList<LogEntry> logEntries = new LinkedList<>();
    public LogParser(Path logDir) {
        try {
            for (Path p: Files.list(logDir).filter(x->x.getFileName().toString().toLowerCase().endsWith(".log")).collect(Collectors.toList())
                 )
                Files.lines(p).forEach(x -> logEntries.add(new LogEntry(x)));
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry l: logEntries
             ) {
            if ((after == null || l.date.after(after)) && (before==null ||l.date.before(before))) {
                set.add(l.ip);
            }
        }
        return set.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry l: logEntries
        ) {
            if ((after == null || l.date.after(after)) && (before==null ||l.date.before(before))) {
                set.add(l.ip);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry l: logEntries
        ) {
            if ((after == null || l.date.after(after)) && (before==null ||l.date.before(before)) && l.user.equals(user)) {
                set.add(l.ip);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry l: logEntries
        ) {
            if ((after == null || l.date.after(after)) && (before==null ||l.date.before(before)) && l.event.equals(event)) {
                set.add(l.ip);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry l: logEntries
        ) {
            if ((after == null || l.date.after(after)) && (before==null ||l.date.before(before)) && l.status.equals(status)) {
                set.add(l.ip);
            }
        }
        return set;
    }

    @Override
    public Set<String> getAllUsers() {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
             ) {
            set.add(e.user);
        }
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before))) {
                set.add(e.user);
            }
        }
        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        HashSet<Event> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.user.equals(user)) {
                set.add(e.event);
            }
        }
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.ip.equals(ip)) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(Event.LOGIN)) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(Event.DOWNLOAD_PLUGIN) && e.status.equals(Status.OK)) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(Event.WRITE_MESSAGE) && e.status.equals(Status.OK)) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(Event.SOLVE_TASK)) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(Event.SOLVE_TASK) && task == e.task) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(Event.DONE_TASK)) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        HashSet<String> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(Event.DONE_TASK) && e.task == task) {
                set.add(e.user);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        HashSet<Date> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.event.equals(event) && e.user.equals(user)) {
                set.add(e.date);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        HashSet<Date> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.status == Status.FAILED) {
                set.add(e.date);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        HashSet<Date> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.status == Status.ERROR) {
                set.add(e.date);
            }
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        ArrayList<Date> dates = new ArrayList<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.user.equals(user) && e.event == Event.LOGIN) {
                dates.add(e.date);
            }
        }
        Collections.sort(dates);
        return dates.size() > 0 ? dates.get(0): null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        ArrayList<Date> dates = new ArrayList<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.user.equals(user) && e.event == Event.SOLVE_TASK && e.task == task) {
                dates.add(e.date);
            }
        }
        Collections.sort(dates);
        return dates.size() > 0 ? dates.get(0) : null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        ArrayList<Date> dates = new ArrayList<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.user.equals(user) && e.event == Event.DONE_TASK && e.task == task) {
                dates.add(e.date);
            }
        }
        Collections.sort(dates);
        return dates.size() > 0 ? dates.get(0) : null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        HashSet<Date> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.user.equals(user) && e.event == Event.WRITE_MESSAGE) {
                set.add(e.date);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        HashSet<Date> set = new HashSet<>();
        for (LogEntry e: logEntries
        ) {
            if ((after == null || e.date.after(after)) && (before==null ||e.date.before(before)) && e.user.equals(user) && e.event == Event.DOWNLOAD_PLUGIN) {
                set.add(e.date);
            }
        }
        return set;
    }
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return (int)logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)))
                .map(LogEntry::getEvent)
                .distinct()
                .count();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)))
                .map(LogEntry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.ip.equals(ip))
                .map(LogEntry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.user.equals(user))
                .map(LogEntry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.status == Status.FAILED)
                .map(LogEntry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.status == Status.ERROR)
                .map(LogEntry::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int)logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.task == task && logEntry.event == Event.SOLVE_TASK)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int)logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.task == task && logEntry.event == Event.DONE_TASK)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.event == Event.SOLVE_TASK)
                .collect(Collectors.toMap(LogEntry::getTask, logEntry -> 1, Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> (after == null || logEntry.date.after(after)) && (before == null || logEntry.date.before(before)) && logEntry.event == Event.DONE_TASK)
                .collect(Collectors.toMap(LogEntry::getTask, logEntry -> 1, Integer::sum));
    }

    public class LogEntry {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int task;
        private Status status;

        public LogEntry(String str) {
            String [] strings = str.split("\\t");
            ip = strings[0];
            user = strings[1];
            try {
                date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(strings[2]);
            } catch (ParseException e) {
                date = null;
                e.printStackTrace();
            }
            event = Event.valueOf(strings[3].split(" ")[0]);
            if (event == Event.DONE_TASK || event == Event.SOLVE_TASK ) {
                task = Integer.parseInt(strings[3].split(" ")[1]);
            } else {
                task = -1;
            }
            status = Status.valueOf(strings[4]);
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getTask() {
            return task;
        }

        public Status getStatus() {
            return status;
        }
    }
}