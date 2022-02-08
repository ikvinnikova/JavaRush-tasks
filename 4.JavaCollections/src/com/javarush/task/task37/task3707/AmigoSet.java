package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Cloneable, Serializable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int cap = Math.max(16, (int)Math.ceil(collection.size()/.75f));
        map = new HashMap<>(cap);
        this.addAll(collection);
    }

    public boolean add(E e) {
        if (map.put(e, PRESENT) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AmigoSet<E> object=null;
        try {
            object=new AmigoSet<>();
            object.map=(HashMap<E,Object>) map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return object;

    }
    private void writeObject (ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
        int cap = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        outputStream.writeInt(cap);
        outputStream.writeFloat(loadFactor);
        outputStream.writeInt(size());
        for (E e:
             map.keySet()) {
            outputStream.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        int cap = inputStream.readInt();
        float loadFactor = inputStream.readFloat();
        int size = inputStream.readInt();
        map = new HashMap<E, Object>(cap, loadFactor);
        for (int i = 0; i < size; i++) {
            add((E) inputStream.readObject());
        }
    }
}
