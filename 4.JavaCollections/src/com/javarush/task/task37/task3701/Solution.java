package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    @Override
    public Iterator<T> iterator() {
        RoundIterator roundIterator = new RoundIterator();
        return roundIterator;
    }
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    public class RoundIterator implements Iterator <T> {
        Iterator<T> iterator = Solution.super.iterator();

        public boolean hasNext() {
            return !Solution.super.isEmpty();
        }
        public T next() {
            if (iterator.hasNext()) {
                return iterator.next();
            } else {
                iterator = Solution.super.iterator();
            }
            return iterator.next();
        }
        public void remove() {

            iterator.remove();
        }

    }
}
