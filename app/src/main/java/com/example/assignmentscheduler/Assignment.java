package com.example.assignmentscheduler;

import java.util.Calendar;
import java.util.TimeZone;



public class Assignment {
    private String assignName;
    private Calendar startDate;
    private Calendar endDate;
    private AssignType type;
    private AssignLength length;

    public Assignment() {
        this.assignName = "";
        this.startDate = Calendar.getInstance();
        this.endDate = null;
        this.type = assignType.None;
        this.length = assignLength.None;
    }

    public Assignment(String name, String[] sDate, String[] eDate, String aType, String aLength) {
        assignName = name;
        startDate = convertToDate(sDate);
        endDate = convertToDate(eDate);
        type = assignType.valueOf(aType);
        length = assignLength.valueOf(aLength);
    }

    public void modifyAssignment(String name, String[] sDate, String[] eDate, String aType, String aLength) {
        assignName = name;
        startDate = convertToDate(sDate);
        endDate = convertToDate(eDate);
        type = assignType.valueOf(aType);
        length = assignLength.valueOf(aLength);
    }

    private Calendar convertToDate(String[] date) {
        Calendar finalDate = Calendar.getInstance(TimeZone.getDefault());
        int month, day, year;
        month = Integer.parseInt(date[0]);
        day = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);
        finalDate.set(year, month, day);
        return finalDate;
    }

    public String getName() {
        return assignName;
    }
    public Calendar getStartDate() {
        return startDate;
    }
    public String printSDate() {
        String date = "";
        date += startDate.get(Calendar.MONTH) + " ";
        date += startDate.get(Calendar.DAY_OF_MONTH) + " ";
        date += startDate.get(Calendar.YEAR) + " ";
        return date;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public String printEDate() {
        String date = "";
        date += endDate.get(Calendar.MONTH) + " ";
        date += endDate.get(Calendar.DAY_OF_MONTH) + " ";
        date += endDate.get(Calendar.YEAR) + " ";
        return date;
    }
    public String getType() {
        return type.toString();
    }
    public String getLength() {
        return length.toString();
    }
}
