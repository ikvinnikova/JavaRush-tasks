package com.javarush.task.task33.task3309;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
Комментарий внутри xml
*/
@XmlRootElement
@XmlType(name = "cat")
public class Cat {
@XmlElement
    String name = "Murka";
@XmlElement
    int age = 5;
}
