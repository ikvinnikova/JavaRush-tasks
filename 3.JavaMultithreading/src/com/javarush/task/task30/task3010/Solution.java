package com.javarush.task.task30.task3010;

import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        boolean res = true;
        try {
            String s = args[0];
            char max = '0';
            for (int i = 0; i < s.length(); i++) {
                if (Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
                    if (max < Character.toLowerCase(s.charAt(i))) {
                        max = Character.toLowerCase(s.charAt(i));
                    }
                } else {
                    i = s.length();
                    res = false;
                }
            }
            if (res) {
                if (Character.isDigit(max)) {
                    if (max < '1') max = '1';
                    System.out.println(Character.digit(max, 10) + 1);
                } else {
                    System.out.println(11 + (max - 'a'));
                }
            } else {
                System.out.println("incorrect");
            }
        } catch (Exception e) {}
    }
}