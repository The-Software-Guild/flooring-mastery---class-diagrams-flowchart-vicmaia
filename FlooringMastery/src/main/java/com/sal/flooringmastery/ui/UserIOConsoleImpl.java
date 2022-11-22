package com.sal.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 *
 * @author vicmaia
 */
public class UserIOConsoleImpl implements UserIO {

    private final Scanner input = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        boolean valid = false;
        int result = 0;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = Integer.parseInt(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value '%s' is not a number.\n", value);
            }
        } while (!valid);
        return result;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean valid = false;
        int result = 0;

        do {
            result = readInt(prompt);
            if (result >= min && result <= max) {
                valid = true;
            } else {
                System.out.printf("The value must be between %d and %d.\n", min, max);
            }
        } while (!valid);

        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean isValid = false;
        BigDecimal result = BigDecimal.ZERO;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = new BigDecimal(value);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value '%s' is not a number. \n", ex);
            }
        } while (!isValid);
        return result;
    }

    @Override
    public BigDecimal readOptionalBigDecimal(String prompt) {
        boolean isValid = false;
        BigDecimal result = BigDecimal.ZERO;
        do {
            String value = null;
            try {
                value = readString(prompt);
                if (!value.equals("")) {
                    result = new BigDecimal(value);
                    isValid = true;
                } else {
                    result = BigDecimal.ZERO;
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.printf("The value '%s' is not a number. \n");
            }
        } while (!isValid);
        return result;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        boolean isValid = false;
        LocalDate result = LocalDate.MAX;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = LocalDate.parse(value, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("The value '%s' is not a Date. \n", ex);
            }
        } while (!isValid);
        return result;
    }

    @Override
    public String readState(String prompt) {
        boolean valid = false;
        String result = "";
        do {
            String value = null;

            value = readString(prompt);
            if (value.equals("TX") || value.equals("WA") || value.equals("KY") || value.equals("CA")) {
                result = value;
                valid = true;
            } else {
                System.out.printf("You Must Select One Of The Options Provided!  ");
            }

        } while (!valid);
        return result;
    }

    @Override
    public String readOptionalState(String prompt) {
        boolean valid = false;
        String result = "";
        do {
            String value = null;

            value = readString(prompt);
            if (value.equals("TX") || value.equals("WA") || value.equals("KY") || value.equals("CA")) {
                result = value;
                valid = true;
            } else if(value.equals("")) {
                result = value;
                valid = true;
            } else {
                System.out.printf("Invalid input. Please try again.");
            }

        } while (!valid);
        return result;
    }

    @Override
    public String readProduct(String prompt) {
        boolean valid = false;
        String result = "";
        do {
            String value = null;

            value = readString(prompt);
            if (value.equals("Carpet") || value.equals("Laminate") || value.equals("Tile") || value.equals("Wood")) {
                result = value;
                valid = true;
            } else {
                System.out.printf("Invalid input. Please try again.");
            }

        } while (!valid);
        return result;
    }

    @Override
    public String readOptionalProduct(String prompt) {
        boolean valid = false;
        String result = "";
        do {
            String value = null;

            value = readString(prompt);
            if (value.equals("Carpet") || value.equals("Laminate") || value.equals("Tile") || value.equals("Wood")) {
                result = value;
                valid = true;
            } else if(value.equals("")) {
                result = value;
                valid = true;
            } else {
                System.out.printf("Invalid input. Please try again.");
            }

        } while (!valid);
        return result;
    }
}
