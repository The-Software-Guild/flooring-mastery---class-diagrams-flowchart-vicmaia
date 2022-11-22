package com.sal.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 *
 * @author vicmaia
 */
public interface UserIO {

    void print(String msg);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    String readOptionalState(String prompt);

    BigDecimal readBigDecimal(String prompt);

    BigDecimal readOptionalBigDecimal(String prompt);

    LocalDate readLocalDate(String prompt);

    String readState(String prompt);

    String readProduct(String prompt);

    String readOptionalProduct(String prompt);

    String readString(String prompt);

}
