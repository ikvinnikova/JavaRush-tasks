package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1234);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        List<Integer> n = new ArrayList<>();
        int a = 1;
        n.add(a);
        for (int i = 0; i < 7; i++) {
            n.add(a*3);
            a = a * 3;
        }
        List<Integer> result = new ArrayList<>();
        a = number;
        int b = 0;
        while (a > 0) {
            b = a % 3;
            a = a / 3;
            if (b <= 1) {
                result.add(b);
            } else {
                result.add(-1);
                a++;
            }
        }

        System.out.print(number + " =");
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) > 0) {
                System.out.print(" + " + n.get(i));
            } else {
                if (result.get(i) < 0) {
                    System.out.print(" - " + n.get(i));
                }
            }
        }
    }
}