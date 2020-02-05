package com.triceratops.dinocityserver.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static double round(double number){
        BigDecimal bd = BigDecimal.valueOf(number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
