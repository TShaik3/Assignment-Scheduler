package com.example.assignmentscheduler;

import java.util.Calendar;
import java.util.TimeZone;

public class Assignment {
    private static int id_count = 0;
    private int id;
    private String assignName;
    private Calendar startDate;
    private Calendar endDate;
    private AssignType type;
    private AssignLength length;

    public Assignment() {
        this.id = ++id_count;
        this.assignName = "";
        this.startDate = Calendar.getInstance();
        this.endDate = null;
        this.type = AssignType.None;
        this.length = AssignLength.None;
    }

    public void modifyAssignment(String name, String[] sDate, String[] eDate, String aType, String aLength) {
        assignName = name;
        startDate = convertToDate(sDate);
        endDate = convertToDate(eDate);
        type = AssignType.valueOf(aType);
        length = AssignLength.valueOf(aLength);
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

    public int getProgress() {
        long totalDifferenceInHours = getDifferenceInMillis() / (1000 * 60 * 60);
        long currentDurationInHours = Calendar.getInstance().getTimeInMillis() - startDate.getTimeInMillis() / (1000 * 60 * 60);
        return (int) (currentDurationInHours / totalDifferenceInHours)  * 100;
    }

    public String getTimeBetween() {
        getDifferenceInMillis();
        long differenceInHours = getDifferenceInMillis() / (1000 * 60 * 60);
        long differenceInDays = getDifferenceInMillis() / (1000 * 60 * 60 * 24);

        if (differenceInDays < 1) {
            return differenceInHours + " hrs";
        }
        else {
            return differenceInDays + " days";
        }
    }

    private long getDifferenceInMillis() {
        long timeInMillis1 = startDate.getTimeInMillis();
        long timeInMillis2 = endDate.getTimeInMillis();
        return timeInMillis2 - timeInMillis1;
    }

    public String getName() {
        return assignName;
    }
    public Calendar getStartDate() {
        return startDate;
    }
    public String printSDate() {
        String date = "";
        date += startDate.get(Calendar.MONTH) + "/";
        date += startDate.get(Calendar.DAY_OF_MONTH) + "/";
        date += startDate.get(Calendar.YEAR);
        return date;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public String printEDate() {
        String date = "";
        date += endDate.get(Calendar.MONTH) + "/";
        date += endDate.get(Calendar.DAY_OF_MONTH) + "/";
        date += endDate.get(Calendar.YEAR);
        return date;
    }
    public String getType() {
        return type.toString();
    }
    public String getLength() {
        return length.toString();
    }
    public String printTotalDate() {
        return printSDate() + " - " + printEDate();
    }

    public int getID() {
        return id;
    }
}
