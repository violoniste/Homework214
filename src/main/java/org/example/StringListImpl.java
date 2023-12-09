package org.example;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] list = new String[1];
    private int size = 0;

    @Override
    public String add(String item) {
        if (size == list.length)
            grow();

        list[size] = item;
        size++;

        return item;
    }

    private void grow() {
        list = Arrays.copyOf(list, list.length * 2);
    }

    @Override
    public String add(int index, String item) {
        if (index >= size)
            throw new IndexOutOfBoundsException();

        if (size == list.length)
            grow();

        for (int i = list.length - 1; i > index; i--) {
            list[i] = list[i - 1];
        }

        list[index] = item;

        size++;

        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index >= size)
            throw new IndexOutOfBoundsException();

        list[index] = item;

        return item;
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if (index == -1)
            throw new NotFoundException();

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        size--;

        return item;
    }

    @Override
    public String remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();

        String item = get(index);

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        size--;

        return item;
    }

    @Override
    public boolean contains(String item) {
        for (String s : list) {
            if (s != null && s.equals(item))
                return true;
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] != null && list[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public String get(int index) {
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null)
            throw new NullPointerException();

        if (size != otherList.size())
            return false;

        for (int i = 0; i < list.length; i++) {
            String item = list[i];
            String other = otherList.get(i);
            if (item != null && other != null && !item.equals(other))
                return false;
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        list = new String[1];
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }
}
