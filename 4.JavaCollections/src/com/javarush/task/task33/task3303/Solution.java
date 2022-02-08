package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj = objectMapper.readValue(new File(fileName), clazz);
        return (T)obj;
    }

    public static void main(String[] args) throws IOException{
        Object obj = convertFromJsonToNormal("D:/arx/2.txt", Object.class);
        System.out.println(obj);
    }
}
