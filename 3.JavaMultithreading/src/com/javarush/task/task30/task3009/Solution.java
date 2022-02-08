package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix (String s) {
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= 36; i++) {
            if (isPalindrome(s, i)) {
                set.add(i);
            }
        }
        return set;
    }

    private static boolean isPalindrome (String s, int radix) {
        try {
            BigInteger i = new BigInteger(s, 10);
            String digit = i.toString(radix);
            for (int j = 0; j < digit.length() / 2; j++) {
                if (digit.charAt(j) != digit.charAt(digit.length() - j - 1)) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}