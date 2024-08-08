package com.demoApp.tools.service;

import com.demoApp.tools.model.ToolsDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ToolServiceTest {
    @Mock
    private  ToolService service;

   @Test
    public void testDiscountAmount() {
        double result = service.discountAmount(10,30);
        assertEquals(0.0, result, "discount");
    }

 /*  @Test
    public void testCheckoutScen1() {
        ToolsDetails result = service.calculateCheckoutDetails("JAKR",5, LocalDate.parse("9/3/15"),101);
         assertEquals("", result.getFinalAmount(), "discount");
    }

    @Test
    public void testCheckoutScen2() {
        ToolsDetails result = service.calculateCheckoutDetails("LADW",5, LocalDate.parse("7/2/20"),10);
        assertEquals("", result.getFinalAmount(), "discount");
    }


    @Test
    public void testCheckoutScen3() {
        ToolsDetails result = service.calculateCheckoutDetails("CHNS",5, LocalDate.parse("7/2/15"),25);
        assertEquals("", result.getFinalAmount(), "discount");
    }


    @Test
    public void testCheckoutScen4() {
        ToolsDetails result = service.calculateCheckoutDetails("JAKD",6, LocalDate.parse("9/3/15"),0);
        assertEquals("", result.getFinalAmount(), "discount");
    }*/


  /*  @Test
    public void testCheckoutScen5() {
       ToolsDetails result = service.calculateCheckoutDetails("JAKR",4, LocalDate.parse(LocalDate.parse("7/2/20",parser).format(formatter)),50);
        assertEquals("2.99", result.getFinalAmount(), "discount");
    }*/



}

