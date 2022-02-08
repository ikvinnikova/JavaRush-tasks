package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            int num = (int)Math.floor(Math.random() * 150);
            while (num < 48 || num > 57) {
                num = (int)Math.floor(Math.random() * 150);
            }
            stream.write(num);

            num = (int)Math.floor(Math.random() * 150);
            while (num < 65 || num > 90) {
                num = (int)Math.floor(Math.random() * 150);
            }
            stream.write(num);

            num = (int)Math.floor(Math.random() * 150);
            while (num < 97 || num > 122) {
                num = (int)Math.floor(Math.random() * 150);
            }
            stream.write(num);

            for (int i = 0; i < 5; i++) {
                num = (int)Math.floor(Math.random() * 150);
                while (num < 48 || num > 57 && num < 65 || num > 90 && num < 97 || num > 122) {
                    num = (int) Math.floor(Math.random() * 150);
                }
                stream.write(num);
            }
            stream.flush();
            stream.close();
        } catch (IOException e) {

        }
        return stream;
    }
}
