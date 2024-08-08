package com.demoApp.tools.model;

import lombok.Data;

@Data
public class ToolWage {
    public ToolWage(String toolType, String dailyCharge, String weeklyCharge, String weekendCharge, String holidayCharge) {
        ToolType = toolType;
        DailyCharge = dailyCharge;
        WeeklyCharge = weeklyCharge;
        WeekendCharge = weekendCharge;
        HolidayCharge = holidayCharge;
    }

    private String ToolType;
    private String DailyCharge;
    private String WeeklyCharge;

    public String getToolType() {
        return ToolType;
    }

    public void setToolType(String toolType) {
        ToolType = toolType;
    }

    public String getDailyCharge() {
        return DailyCharge;
    }

    public void setDailyCharge(String dailyCharge) {
        DailyCharge = dailyCharge;
    }

    public String getWeeklyCharge() {
        return WeeklyCharge;
    }

    public void setWeeklyCharge(String weeklyCharge) {
        WeeklyCharge = weeklyCharge;
    }

    public String getWeekendCharge() {
        return WeekendCharge;
    }

    public void setWeekendCharge(String weekendCharge) {
        WeekendCharge = weekendCharge;
    }

    public String getHolidayCharge() {
        return HolidayCharge;
    }

    public void setHolidayCharge(String holidayCharge) {
        HolidayCharge = holidayCharge;
    }

    private String WeekendCharge;
    private String HolidayCharge;
}
