package com.javarush.task.task35.task3512;

public class Generator<T> {

    public Generator(Class<T> f) {
        this.f = f;
    }

    private Class<T> f;

    T newInstance() throws IllegalAccessException, InstantiationException {
        return f.newInstance();
    }
}
