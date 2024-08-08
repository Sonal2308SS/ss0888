package com.demoApp.tools.service;

import com.demoApp.tools.model.ToolWage;
import com.demoApp.tools.model.Tools;
import com.demoApp.tools.model.ToolsDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.PublicKey;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class ToolService {

    private final  CalenderService calenderService;
@Autowired
    public ToolService(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    public int  calculateChargeDays ( int Weekdays, int Weekends, int Holidays , ToolsDetails toolsDetails) {




        int totalChargeDays = 0;
        if (toolsDetails.getWeeklyCharge().equalsIgnoreCase("YES")) {
            totalChargeDays = totalChargeDays + Weekdays;
        }
        if (toolsDetails.getWeekendCharge().equalsIgnoreCase("YES")) {
            totalChargeDays = totalChargeDays + Weekends;
        }
        if (toolsDetails.getHolidayCharge().equalsIgnoreCase("YES")) {
            totalChargeDays = totalChargeDays + Holidays;
        }
        return totalChargeDays;
    }

    public  double discountAmount(double percent, double amount)
    {
        return amount * (percent / 100.0);

    }

    public HashMap<String, List<String>> createData() {
        Tools s1 = new Tools("CHNS", "CHAINSAW", "Stihl");
        Tools s2 = new Tools("LADW", "LADDER", "Werner");
        Tools s3 = new Tools("JAKD", "JACKHAMMER", "DeWalt");
        Tools s4 = new Tools("JAKR", "JACKHAMMER", "Ridgid");

        List<Tools> l = new ArrayList<Tools>(Arrays.asList(s1, s2, s3, s4));
        HashMap<String, List<String>> hm = new HashMap<String, List<String>>();

        for (Tools s : l) {
            if (hm.containsKey(s.getToolCode())) {
                hm.get(s.getToolCode()).add(s.getToolType() + "-" + s.getToolBrand());
            } else {
                hm.put(s.getToolCode(), new ArrayList<String>());
                hm.get(s.getToolCode()).add(s.getToolType() + " -" + s.getToolBrand());
            }
        }
        return hm;
    }

    public ToolsDetails calculateCheckoutDetails (String toolCode,  Integer rentalDays,
                                                  LocalDate checkoutDate,  Integer DiscountPerCent)
    {
        DecimalFormat formatter
                = new DecimalFormat("$#,##0.00");
        DecimalFormat decFormat = new DecimalFormat("#%");

        ToolsDetails toolsDetails = new ToolsDetails();
        HashMap<String, List<String>> tools = createData();
        HashMap<String, List<String>> toolsWage = createToolWage();
        if (!tools.isEmpty()) {
            if (tools.containsKey(toolCode)) {
                System.out.println(tools.get(toolCode));
                String S = tools.get(toolCode).get(0);
                String[] a = S.split("-");
                toolsDetails.setToolType(a[0]);
                toolsDetails.setToolCode(toolCode);
                toolsDetails.setToolBrand(a[1]);
                if (toolsWage.containsKey(toolsDetails.getToolType().trim())) {
                    //System.out.println(tools.get(toolCode));

                    String wageDetails = toolsWage.get(toolsDetails.getToolType().trim()).get(0);
                    String[] b = wageDetails.split("-");
                    toolsDetails.setDailyRentalCharge(b[0]);
                    toolsDetails.setWeeklyCharge(b[1]);
                    toolsDetails.setWeekendCharge(b[2]);
                    toolsDetails.setHolidayCharge(b[3]);
                }
                toolsDetails.setDueDate(calenderService.calculateDueDate(rentalDays, checkoutDate));
                toolsDetails.setRentalDays(rentalDays);
                toolsDetails.setCheckoutDate(checkoutDate);
                List<LocalDate> holidaysAvailable = calenderService.addHolidays();
                int holidays = calenderService.countHolidaysBetween(checkoutDate, toolsDetails.getDueDate().plusDays(1), holidaysAvailable);
                System.out.println(holidays);
                int weekdays = calenderService.countBusinessDaysBetween(checkoutDate, toolsDetails.getDueDate(),holidaysAvailable );
                System.out.println(weekdays);
                int weekends = calenderService.countWeekendsBetween(checkoutDate, toolsDetails.getDueDate());
                System.out.println(weekends);
                toolsDetails.setDiscountPercent(Double.valueOf(DiscountPerCent));
                int totalChargeDays =  calculateChargeDays(weekdays, weekends, holidays, toolsDetails);
                double amount = Double.parseDouble((toolsDetails.getDailyRentalCharge().replaceAll("[$,]", "")));
                double finalCharge;
                amount = amount * totalChargeDays;
                toolsDetails.setChargeDays(totalChargeDays);
                toolsDetails.setPreDiscountAmount(formatter.format(amount));
                toolsDetails.setDiscountAmount(formatter.format(discountAmount(toolsDetails.getDiscountPercent(),amount)));
                toolsDetails.setFinalAmount(formatter.format(amount - discountAmount(toolsDetails.getDiscountPercent(),amount)));
            }
        }
        return toolsDetails;
    }

public  HashMap<String, List<String>>  createToolWage()
{

    ToolWage w1 = new ToolWage("LADDER", "$1.99", "YES","YES","NO");
    ToolWage w2 = new ToolWage("CHAINSAW" ,"1.49","YES","NO","YES");
    ToolWage w3 = new ToolWage("JACKHAMMER", "2.99","YES","NO","YES");

    List<ToolWage> wages  = new ArrayList<ToolWage> (Arrays.asList(w1,w2,w3));
    HashMap<String, List<String>> hmToolWage = new HashMap<String, List<String>>();

    for(ToolWage s : wages) {
        if (hmToolWage.containsKey(s.getToolType())) {
            hmToolWage.get(s.getToolType()).add(s.getDailyCharge()+"-"+s.getHolidayCharge()+"-"+s.getWeekendCharge()+"-"+s.getWeekendCharge());
        } else {
            hmToolWage.put(s.getToolType(), new ArrayList<String>());
            hmToolWage.get(s.getToolType()).add(s.getDailyCharge() +" -"+s.getHolidayCharge()+"-"+s.getWeekendCharge()+"-"+s.getWeeklyCharge());
        }
    }
    return hmToolWage;
}


}
