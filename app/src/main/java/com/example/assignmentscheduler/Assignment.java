package com.example.assignmentscheduler;

import android.os.Build;

import java.time.*;

public class Assignment {

    enum assignType { None, Homework, Essay, Project, Exam}
    enum assignLength { None, Short, Medium, Long, Extra_Long}

    private String assignName;
    private LocalDate startDate;
    private LocalDateTime endDate;
    private assignType type;
    private assignLength length;

    public Assignment() {
        assignName = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startDate = LocalDate.now();
            endDate = LocalDateTime.now();
        }
        assignType type = assignType.None;
        assignLength length = assignLength.None;
    }

    public Assignment(String name, String[] sDate, String[] eDate, String aType, String aLength) {
        assignName = name;
        startDate = convertToDate(sDate);
        endDate = convertToDateTime(eDate);
        type = assignType.valueOf(aType);
        length = assignLength.valueOf(aLength);
    }

    public void modifyAssignment(String name, String[] sDate, String[] eDate, String aType, String aLength) {
        assignName = name;
        startDate = convertToDate(sDate);
        endDate = convertToDateTime(eDate);
        type = assignType.valueOf(aType);
        length = assignLength.valueOf(aLength);
    }

    private LocalDate convertToDate(String[] date) {
        LocalDate finalDate = null;
        int month, day, year;
        month = Integer.parseInt(date[0]);
        day = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            finalDate = LocalDate.of(day, month, year);
        }
        return finalDate;
    }

    private LocalDateTime convertToDateTime(String[] dateTime) {
        LocalDateTime finalDate = null;
        int month, day, year, hour, min;
        month = Integer.parseInt(dateTime[0]);
        day = Integer.parseInt(dateTime[1]);
        year = Integer.parseInt(dateTime[2]);
        hour = Integer.parseInt(dateTime[3]);
        min = Integer.parseInt(dateTime[4]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            finalDate = LocalDateTime.of(day, month, year, hour, min);
        }
        return finalDate;
    }

    public String getName() {
        return assignName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public String getType() {
        return type.toString();
    }
    public String getLength() {
        return length.toString();
    }
}
