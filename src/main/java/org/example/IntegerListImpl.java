package org.example;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] list;
    private int size = 0;

    public IntegerListImpl(int initSize) {
        list = new Integer[initSize];
    }

    public void testSorting() {
        Integer[] testList = new Integer[100000];
        for (int i = 0; i < testList.length; i++) {
            testList[i] = Math.toIntExact(Math.round(Math.random() * 10000000));
        }

        list = Arrays.copyOf(testList, testList.length);

        System.out.println("Start sorting sortBubble...");
        long start = System.currentTimeMillis();
        sortBubble(list);
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        System.out.println();

        System.out.println("Start sorting sortSelection...");
        start = System.currentTimeMillis();
        sortSelection(list);
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        System.out.println();

        System.out.println("Start sorting sortInsertion...");
        start = System.currentTimeMillis();
        sortInsertion(list);
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        System.out.println();
    }

    @Override
    public Integer add(Integer item) {
        if (item == null)
            throw new NullPointerException();

        if (size == list.length)
            grow();

        list[size] = item;
        size++;

        return item;
    }

    private void grow() {
        list = Arrays.copyOf(list, (int) (list.length * 1.5f));
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null)
            throw new NullPointerException();

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
    public Integer set(int index, Integer item) {
        if (item == null)
            throw new NullPointerException();

        if (index >= size)
            throw new IndexOutOfBoundsException();

        list[index] = item;

        return item;
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null)
            throw new NullPointerException();

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
    public Integer remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();

        Integer item = get(index);

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        size--;

        return item;
    }

    @Override
    public boolean contains(Integer item) {
        if (item == null)
            throw new NullPointerException();

        Integer[] arr = Arrays.copyOf(list, size);
        quickSort(arr, 0, size - 1);

        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(arr[mid])) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null)
            throw new NullPointerException();

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null)
            throw new NullPointerException();

        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] != null && list[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();

        return list[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null)
            throw new NullPointerException();

        if (size != otherList.size())
            return false;

        for (int i = 0; i < size; i++) {
            Integer item = list[i];
            Integer other = otherList.get(i);
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
        list = new Integer[1];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(list, size);
    }

    private void sortBubble(Integer[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j] > list[j + 1]) {
                    swapElements(list, j, j + 1);
                }
            }
        }
    }

    private void sortSelection(Integer[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < list[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(list, i, minElementIndex);
        }
    }

    private void sortInsertion(Integer[] list) {
        for (int i = 1; i < size; i++) {
            int temp = list[i];
            int j = i;
            while (j > 0 && list[j - 1] >= temp) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = temp;
        }
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
