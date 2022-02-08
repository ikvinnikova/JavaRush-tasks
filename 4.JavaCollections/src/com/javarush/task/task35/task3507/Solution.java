package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        //Set<? extends Animal> allAnimals = getAllAnimals("D:\\arx\\1");

        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet();
        File dir = new File(pathToAnimals); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        for (File file : lst
        ) {
            if (file.getName().endsWith(".class")) {
                String classPath = file.getAbsolutePath();
                final String finalClassPath = classPath;
                try {
                    ClassLoader myClassLoader = new ClassLoader() {
                        @Override
                        protected Class<?> findClass(String name) throws ClassNotFoundException {
                            byte[] b = new byte [0];
                            try {
                                b = getClassBytes(finalClassPath);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                                return super.findClass(name);
                            }
                            return defineClass(null, b, 0, b.length);
                        }
                    };
                    String className = file.getName().replace(".class", "");
                    Class clazz = myClassLoader.loadClass(className);


                    Type[] types = clazz.getInterfaces();

                    boolean flag = false;
                    for (Type typ : types
                    ) {
                        if (typ == Animal.class) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        Constructor[] cts = clazz.getConstructors();
                        for (Constructor ct: cts
                             ) {
                            if (ct.getParameterCount() == 0 && Modifier.isPublic(ct.getModifiers())) {

                                set.add((Animal) ct.newInstance());
                            }
                        }
                    }
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

        return set;
    }

    private static byte[] getClassBytes(String className) throws IOException {
        InputStream in = new FileInputStream(className);
        long length = new File(className).length();
        if (length >= Integer.MAX_VALUE) {
            throw new IOException();
        }
        byte[] b = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < length &&
                (numRead = in.read(b, offset, b.length - numRead)) > 0) {
            offset += numRead;
        }
        in.close();
        if (numRead < length) {
            throw new IOException();
        }

        return b;
    }
}
