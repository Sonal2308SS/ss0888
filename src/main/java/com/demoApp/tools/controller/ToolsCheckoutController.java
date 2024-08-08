package com.demoApp.tools.controller;

import com.demoApp.tools.model.ToolsDetails;
import com.demoApp.tools.service.CalenderService;
import com.demoApp.tools.service.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("toolsCheckoutController")
@Slf4j
public class ToolsCheckoutController {


    private final ToolService toolsService;


    @Autowired
    public ToolsCheckoutController(ToolService toolsService
    ) {
        this.toolsService = toolsService;


    }

    @RequestMapping(path = "/GetToolsDetails", method = RequestMethod.GET, produces = "application/json")
    public ToolsDetails GetToolDetails(@RequestParam String toolCode, @RequestParam Integer rentalDays,
                                       @RequestParam LocalDate checkoutDate,
                                       @RequestParam Integer DiscountPerCent) throws IOException {

        if (rentalDays < 1) {
            throw new IllegalArgumentException("rentalDays must be greater than 0");
        }
        if (DiscountPerCent < 0 || DiscountPerCent > 100) {
            throw new IllegalArgumentException("DiscountPerCent must be greater than 0");
        }

        ToolsDetails toolsDetails = new ToolsDetails();
        toolsDetails = toolsService.calculateCheckoutDetails(toolCode, rentalDays, checkoutDate, DiscountPerCent);
System.out.println(toolsDetails);
        return toolsDetails;

    }
}



