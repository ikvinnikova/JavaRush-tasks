package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringWriter sw = new StringWriter();
        if (reader != null) {
            char[] cbuf = new char[1000];
            int n = reader.read(cbuf);
            for (int i = 0; i < n; i++) {
                sw.write(cbuf[i] + key);
            }
            sw.close();
        }
        return sw.toString();
    }
}
