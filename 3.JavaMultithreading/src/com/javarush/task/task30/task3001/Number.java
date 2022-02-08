package com.javarush.task.task30.task3001;

import java.math.BigInteger;

public class Number {
    private NumberSystem numberSystem;
    private String digit;

    public Number(NumberSystem numberSystem, String digit) {
        this.numberSystem = numberSystem;
        this.digit = digit;
    }

    public NumberSystem getNumberSystem() {
        return numberSystem;
    }

    public String getDigit() {
        return digit;
    }

    @Override
    public String toString() {
        return "Number{" +
                "numberSystem=" + numberSystem +
                ", digit='" + digit + '\'' +
                '}';
    }

    public String getDecimalDigit() {
        if (numberSystem == NumberSystemType._10) {
            return digit;
        }
        BigInteger sum = new BigInteger("0");
        int k = 0;
        for (int i = digit.length() - 1; i >= 0 ; i--) {
            int c;
            if (!Character.isDigit(digit.charAt(i))) {
                if (!Character.isAlphabetic(digit.charAt(i))) {
                    throw new NumberFormatException();
                }
                c = 10 + (Character.toLowerCase(digit.charAt(i)) - 'a');
            } else {
                c = Integer.parseInt(digit.substring(i, i + 1));
            }
            if (c >= numberSystem.getNumberSystemIntValue()) {
                throw new NumberFormatException();
            }

            sum = sum.add(BigInteger.valueOf((long) (c * Math.pow(numberSystem.getNumberSystemIntValue(), k))));
            k++;
        }
        return sum.toString();
    }
}
