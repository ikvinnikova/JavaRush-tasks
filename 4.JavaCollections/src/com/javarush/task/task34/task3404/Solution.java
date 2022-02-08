package com.javarush.task.task34.task3404;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Рекурсия для мат. выражения
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        //implement
        if (expression.contains("sin(")) {
            recurse(expression.substring(expression.indexOf("sin("), expression.lastIndexOf(')')), 0);
        } else{
            if (expression.contains("cos(")) {
                recurse(expression.substring(expression.indexOf("cos("), expression.lastIndexOf(')')), 0);
            } else {
                if (expression.contains("tan(")) {
                    recurse(expression.substring(expression.indexOf("tan("), expression.lastIndexOf(')')), 0);
                }
            }
        }

        if (expression.contains("(")) {
            recurse(expression.substring(expression.indexOf('('), expression.lastIndexOf(')')), 0);

        }
    }

    public Solution() {
        //don't delete
    }
}
