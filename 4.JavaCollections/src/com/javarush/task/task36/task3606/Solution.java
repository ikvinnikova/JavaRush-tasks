package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File dir = new File(packageName);
        File[] files = dir.listFiles();
        List<File> fileList = Arrays.asList(files);
        for (File file : fileList
        ) {
            if (file.getName().endsWith(".class")) {
                final Path path = file.toPath();

                ClassLoader myClassLoader = new ClassLoader() {
                    @Override
                    public Class findClass(String name) throws ClassNotFoundException {
                        byte[] b;
                        try {
                            b = Files.readAllBytes(path);

                        } catch (IOException e) {
                            e.printStackTrace();
                            return super.findClass(name);
                        }
                        return defineClass(null, b, 0, b.length);
                    }
                };
                Class clazz = myClassLoader.loadClass(file.getName());
                if (HiddenClass.class.isAssignableFrom(clazz)) {
                    hiddenClasses.add(clazz);
                }
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {

        for (Class clazz : hiddenClasses
        ) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                Class [] types = {};
                try {
                    Constructor constructor = clazz.getDeclaredConstructor(types);
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}

