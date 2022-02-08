package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<>("Root");

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        List<Entry<String>> stack = new ArrayList<>();
        stack.add(this.root);
        Entry<String> el;
        int n = stack.size();
        for (int i = 0; i < n; i++) {
            el = stack.get(i);
            if (el.leftChild != null) {
                stack.add(el.leftChild);
                n++;
            }
            if (el.rightChild != null) {
                stack.add(el.rightChild);
                n++;
            }

        }
        return n - 1;
    }

    @Override
    public boolean add(String s) {
        List<Entry<String>> stack = new ArrayList<>();
        stack.add(this.root);
        Entry<String> el;
        int n = stack.size();
        for (int i = 0; i < n; i++) {
            el = stack.get(i);
            if (el.availableToAddLeftChildren) {
                el.leftChild = new Entry<>(s);
                el.leftChild.parent = el;
                el.availableToAddLeftChildren = false;
                return true;
            }
            if (el.availableToAddRightChildren) {
                el.rightChild = new Entry<>(s);
                el.rightChild.parent = el;
                el.availableToAddRightChildren = false;
                return true;
            }
            if (el.leftChild != null) {
                stack.add(el.leftChild);
                n++;
            }
            if (el.rightChild != null) {
                stack.add(el.rightChild);
                n++;
            }
        }
        return false;
    }

    public String getParent(String s) {
        List<Entry<String>> stack = new ArrayList<>();
        stack.add(this.root);
        Entry<String> el;
        int n = stack.size();
        for (int i = 0; i < n; i++) {
            el = stack.get(i);
            if (el.elementName.equals(s)) {
                return el.parent.elementName;
            }
            if (el.leftChild != null) {
                stack.add(el.leftChild);
                n++;
            }
            if (el.rightChild != null) {
                stack.add(el.rightChild);
                n++;
            }
        }
        return null;
    }

    public Entry<String> getEntry(String s) {
        List<Entry<String>> stack = new ArrayList<>();
        stack.add(this.root);
        Entry<String> el;
        int n = stack.size();
        for (int i = 0; i < n; i++) {
            el = stack.get(i);
            if (el.elementName.equals(s)) {
                return el;
            }
            if (el.leftChild != null) {
                stack.add(el.leftChild);
                n++;
            }
            if (el.rightChild != null) {
                stack.add(el.rightChild);
                n++;
            }
        }
        return null;
    }

    public void removeChildren(String s) {
        Entry<String> el = getEntry(s);
        if (el.leftChild != null && el.leftChild.leftChild == null && el.leftChild.rightChild == null) {
            el.leftChild = null;
        }
        if (el.rightChild != null && el.rightChild.leftChild == null && el.rightChild.rightChild == null) {
            el.rightChild = null;
        }
        if (el.leftChild != null) {
            removeChildren(el.leftChild.elementName);
        }
        if (el.rightChild != null) {
            removeChildren(el.rightChild.elementName);
        }

        el = null;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        String s = (String) o;
        Entry<String> el = getEntry(s);
        if (el == null) {
            return false;
        }
        removeChildren(s);
        Entry<String> parent = getEntry(getParent(s));
        if (parent.leftChild != null && parent.leftChild.elementName.equals(s)) {
            parent.leftChild = null;
        }
        if (parent.rightChild != null && parent.rightChild.elementName.equals(s)) {
            parent.rightChild = null;
        }
        if (!add("s")) {
            makeAddable();
        } else {
            remove("s");
        }
        return true;
    }

    public void makeAddable() {
        List<Entry<String>> stack = new ArrayList<>();
        stack.add(this.root);
        Entry<String> el;
        int n = stack.size();
        for (int i = 0; i < n; i++) {
            el = stack.get(i);
            if (el.leftChild == null && !el.availableToAddLeftChildren) {
                el.availableToAddLeftChildren = true;
            }
            if (el.rightChild == null && !el.availableToAddRightChildren) {
                el.availableToAddRightChildren = true;
            }
            if (el.leftChild != null) {
                stack.add(el.leftChild);
                n++;
            }
            if (el.rightChild != null) {
                stack.add(el.rightChild);
                n++;
            }
        }
    }
}
