package advent_of_code_2022;


import org.checkerframework.checker.index.qual.SubstringIndexUnknown;

import java.util.List;

public class DayTwentyFive {
    public long toDecimal(String snafu) {
        StringBuilder negativeNumber = new StringBuilder();
        StringBuilder positiveNumber = new StringBuilder(snafu);
        for (int i = 0; i < positiveNumber.length(); i++) {
            switch (positiveNumber.charAt(i)) {
                case '-' -> {
                    negativeNumber.append(1);
                    positiveNumber.replace(i, i + 1, "0");
                }
                case '=' -> {
                    negativeNumber.append(2);
                    positiveNumber.replace(i, i + 1, "0");
                }
                default -> negativeNumber.append("0");
            }
        }
        return Long.parseLong(positiveNumber.toString(), 5) - Long.parseLong(negativeNumber.toString(), 5);
    }

    public String toSnafu(long decimal) {
        StringBuilder positiveDigits = new StringBuilder(Long.toString(decimal, 5));
        StringBuilder negativeDigits = new StringBuilder();
        StringBuilder toAdd;

        while (positiveDigits.toString().contains("3") || positiveDigits.toString().contains("4")) {
            toAdd = new StringBuilder();
            FIND_NEXT_NEGATIVE:
            for (int i = positiveDigits.length() - 1; i >= 0; i--) {
                switch (positiveDigits.charAt(i)) {
                    case '3' -> {
                        positiveDigits.replace(i, i + 1, "0");
                        negativeDigits.insert(0, '=');
                        toAdd.insert(0, "10");
                        positiveDigits = new StringBuilder(Long.toString(Long.parseLong(positiveDigits.toString(), 5) + Long.parseLong(toAdd.toString(), 5), 5));
                        break FIND_NEXT_NEGATIVE;
                    }
                    case '4' -> {
                        positiveDigits.replace(i, i + 1, "0");
                        negativeDigits.insert(0, '-');
                        toAdd.insert(0, "10");
                        positiveDigits = new StringBuilder(Long.toString(Long.parseLong(positiveDigits.toString(), 5) + Long.parseLong(toAdd.toString(), 5), 5));
                        break FIND_NEXT_NEGATIVE;
                    }
                    default -> {
                        if (negativeDigits.length() < positiveDigits.length() - i) {
                            negativeDigits.insert(0, '0');
                        }
                        toAdd.insert(0, '0');
                    }
                }
            }
        }

        for (int i = 0; i < positiveDigits.length() - negativeDigits.length(); i++) {
            negativeDigits.insert(0,"0");
        }

        for (int i = 0; i < positiveDigits.length(); i++) {
            char negativeDigit = negativeDigits.charAt(i);
            if(negativeDigit == '-' || negativeDigit == '='){
                positiveDigits.replace(i,i+1,String.valueOf(negativeDigit));
            }
        }
        return positiveDigits.toString();
    }

    public String sum(List<String> snafus) {
        long total = 0;
        for (String snafu : snafus) {
            total += toDecimal(snafu);
        }
        return toSnafu(total);
    }
}
