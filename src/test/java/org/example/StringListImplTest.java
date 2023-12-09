package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringListImplTest {

    private static final String ITEM1 = "Test item 1";
    private static final String ITEM2 = "Test item 2";
    private static final String ITEM3 = "Test item 3";

    private StringList out;

    @BeforeEach
    void setUp() {
        out = new StringListImpl();
    }

    @Test
    void add() {
        Assertions.assertEquals(0, out.size());
        String actualAdd = out.add(ITEM1);
        Assertions.assertEquals(1, out.size());
        Assertions.assertEquals(ITEM1, actualAdd);
        out.add(ITEM1);
        out.add(ITEM1);
        out.add(ITEM1);
        out.add(ITEM1);
        out.add(ITEM1);
        out.add(ITEM1);
        out.add(ITEM1);
        out.add(ITEM1);
        out.add(ITEM2);
        Assertions.assertEquals(10, out.size());
        String actualGet = out.get(9);
        Assertions.assertEquals(ITEM2, actualGet);
    }

    @Test
    void addByIndex() {
        Assertions.assertEquals(out.add(ITEM1), ITEM1);
        Assertions.assertEquals(out.add(ITEM2), ITEM2);
        Assertions.assertEquals(out.add(1, ITEM3), ITEM3);

        Assertions.assertEquals(ITEM1, out.get(0));
        Assertions.assertEquals(ITEM3, out.get(1));
        Assertions.assertEquals(ITEM2, out.get(2));
        Assertions.assertEquals(3, out.size());
    }

    @Test
    void addByIndexShouldThrowIndexOutOfBoundsException() {
        out.add(ITEM1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> out.add(1, ITEM1));
    }

    @Test
    void set() {
        Assertions.assertEquals(out.add(ITEM1), ITEM1);
        Assertions.assertEquals(out.add(ITEM2), ITEM2);
        Assertions.assertEquals(out.set(1, ITEM3), ITEM3);

        Assertions.assertEquals(ITEM1, out.get(0));
        Assertions.assertEquals(ITEM3, out.get(1));
        Assertions.assertEquals(2, out.size());
    }

    @Test
    void setShouldThrowIndexOutOfBoundsException() {
        out.add(ITEM1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> out.set(2, ITEM2));
    }

    @Test
    void removeByItem() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);

        Assertions.assertEquals(ITEM2, out.remove(ITEM2));
        Assertions.assertEquals(ITEM1, out.get(0));
        Assertions.assertEquals(ITEM3, out.get(1));
        Assertions.assertEquals(2, out.size());
    }

    @Test
    void removeByItemShouldThrowNotFoundException() {
        out.add(ITEM1);
        out.add(ITEM2);
        Assertions.assertThrows(NotFoundException.class, () -> out.remove(ITEM3));
    }

    @Test
    void removeByIndex() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);

        Assertions.assertEquals(ITEM2, out.remove(1));
        Assertions.assertEquals(ITEM1, out.get(0));
        Assertions.assertEquals(ITEM3, out.get(1));
        Assertions.assertEquals(2, out.size());
    }

    @Test
    void removeByIndexIndexOutOfBoundsException() {
        out.add(ITEM1);
        out.add(ITEM2);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> out.remove(3));
    }

    @Test
    void contains() {
        out.add(ITEM1);
        out.add(ITEM3);

        Assertions.assertTrue(out.contains(ITEM1));
        Assertions.assertFalse(out.contains(ITEM2));
    }

    @Test
    void indexOf() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);
        int actual = out.indexOf(ITEM2);
        Assertions.assertEquals(1, actual);
    }

    @Test
    void indexOfShouldNotFind() {
        out.add(ITEM1);
        out.add(ITEM3);
        int actual = out.indexOf(ITEM2);
        Assertions.assertEquals(-1, actual);
    }

    @Test
    void lastIndexOf() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);
        int actual = out.lastIndexOf(ITEM2);
        Assertions.assertEquals(1, actual);
    }

    @Test
    void lastIndexOfShouldNotFind() {
        out.add(ITEM1);
        out.add(ITEM3);
        int actual = out.lastIndexOf(ITEM2);
        Assertions.assertEquals(-1, actual);
    }

    @Test
    void get() {
        out.add(ITEM1);
        String actual = out.get(0);
        Assertions.assertEquals(ITEM1, actual);
    }

    @Test
    void getShouldThrowIndexOutOfBoundsException() {
        out.add(ITEM1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> out.get(2));
    }

    @Test
    void equalsTrue() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);

        StringList other = new StringListImpl();
        other.add(ITEM1);
        other.add(ITEM2);
        other.add(ITEM3);

        boolean actual = out.equals(other);
        Assertions.assertTrue(actual);
    }

    @Test
    void equalsFalse() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);

        StringList other = new StringListImpl();
        other.add(ITEM1);
        other.add(ITEM3);

        boolean actual = out.equals(other);
        Assertions.assertFalse(actual);
    }

    @Test
    void equalsFalse2() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);

        StringList other = new StringListImpl();
        other.add(ITEM1);
        other.add(ITEM3);
        other.add(ITEM2);

        boolean actual = out.equals(other);
        Assertions.assertFalse(actual);
    }

    @Test
    void equalsShouldThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> out.equals(null));
    }

    @Test
    void size() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);

        int actual = out.size();
        Assertions.assertEquals(actual, 3);
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(out.isEmpty());
        out.add(ITEM1);
        Assertions.assertFalse(out.isEmpty());
    }

    @Test
    void clear() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);
        Assertions.assertFalse(out.isEmpty());
        out.clear();
        Assertions.assertTrue(out.isEmpty());
    }

    @Test
    void toArray() {
        out.add(ITEM1);
        out.add(ITEM2);
        out.add(ITEM3);
        String[] array = out.toArray();
        Assertions.assertEquals(array.length, 3);
        Assertions.assertEquals(array[0], ITEM1);
        Assertions.assertEquals(array[1], ITEM2);
        Assertions.assertEquals(array[2], ITEM3);
    }

    @Test
    void shouldThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> out.add(null));
        Assertions.assertThrows(NullPointerException.class, () -> out.add(2, null));
        Assertions.assertThrows(NullPointerException.class, () -> out.set(2, null));
        Assertions.assertThrows(NullPointerException.class, () -> out.remove(null));
        Assertions.assertThrows(NullPointerException.class, () -> out.contains(null));
        Assertions.assertThrows(NullPointerException.class, () -> out.indexOf(null));
        Assertions.assertThrows(NullPointerException.class, () -> out.lastIndexOf(null));
        Assertions.assertThrows(NullPointerException.class, () -> out.equals(null));
    }
}