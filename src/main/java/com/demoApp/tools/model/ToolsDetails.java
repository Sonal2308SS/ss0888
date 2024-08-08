package com.demoApp.tools.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class ToolsDetails {
    private String toolCode;
    private String toolBrand;
   private String toolType;
     private String dailyRentalCharge;
    private String weeklyCharge;
    private String holidayCharge;
    private String weekendCharge;
    private int rentalDays;
    private LocalDate checkoutDate;
    private Double discountPercent;
    private int chargeDays;
    private String discountAmount;
    private String finalAmount;
    private LocalDate dueDate;
    private String preDiscountAmount;
}
