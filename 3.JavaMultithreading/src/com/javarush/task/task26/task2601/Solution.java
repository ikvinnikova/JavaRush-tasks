package com.javarush.task.task26.task2601;

import javax.swing.*;
import java.util.*;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
        //Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        array = sort(array);
        for (int i = 0; i < array.length; i++) {
 //           System.out.print(array[i] + " ");
        }

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        int med;
        List<Integer> list = Arrays.asList(array.clone());
        Collections.sort(list);
        if (list.size() % 2 == 0){
            med = (list.get(list.size()/2) + list.get(list.size()/2 - 1)) / 2;
        } else {
            med = list.get(list.size()/2);
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result;
                int res1 = o1 - med < 0 ? med - o1 : o1- med;
                int res2 = o2 - med < 0 ? med - o2 : o2- med;
                if (res1 == res2) {
                    result = o1 - o2;
                } else {
                    result = res1 - res2;
                }
                return result;
            }
        };

        Collections.sort(list, comparator);

        list.toArray(array);
        return array;
    }
}
