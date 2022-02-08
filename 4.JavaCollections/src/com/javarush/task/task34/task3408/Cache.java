package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        V val = cache.get(key);
        if (val == null) {
            cache.put(key, clazz.getConstructor(key.getClass()).newInstance(key));
        }
        return val;
    }

    public boolean put(V obj){
        //TODO add your code here

        Method m = null;
        try {
            m = obj.getClass().getDeclaredMethod("getKey");
            m.setAccessible(true);
            K key = (K) m.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }

        return false;
    }

    public int size() {
        return cache.size();
    }
}
