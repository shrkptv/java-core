import org.junit.jupiter.api.*;
import tasks.linkedlist.MyLinkedList;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    private MyLinkedList<Integer> list;

    @BeforeEach
    public void createList()
    {
        list = new MyLinkedList<>();
    }

    @Test
    public void testAddFirst()
    {
        list.addFirst(1);
        list.addFirst(3);
        list.addFirst(2);

        assertEquals(2, list.getFirst());
        assertEquals(3, list.size());
        assertEquals(1, list.getLast());
    }

    @Test
    public void testAddLast()
    {
        list.addLast(100);
        list.addLast(5);
        list.addLast(29);
        list.addLast(50);

        assertEquals(50, list.getLast());
        assertEquals(100, list.getFirst());
        assertEquals(4, list.size());
    }

    @Test
    public void testAddAndGet()
    {
        list.addFirst(44);
        list.addLast(52);
        list.add(1, 90);

        assertEquals(3, list.size());
        assertEquals(90, list.get(1));
        assertEquals(44, list.get(0));
        assertEquals(52, list.get(2));
    }

    @Test
    public void testRemoveFirst()
    {
        list.addLast(33);
        list.addLast(77);

        assertEquals(33, list.removeFirst());
        assertEquals(77, list.getFirst());
        assertEquals(1, list.size());
    }

    @Test
    public void testRemoveLast()
    {
        list.addLast(10);
        list.addLast(30);
        list.addLast(20);

        assertEquals(20, list.removeLast());
        assertEquals(30, list.getLast());
        assertEquals(2, list.size());
        assertEquals(10, list.getFirst());
    }

    @Test
    public void testRemove()
    {
        list.addFirst(42);
        list.addLast(88);
        list.add(1, 60);

        assertEquals(60, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(88, list.get(1));
    }

    @Test
    public void testGenericTypes()
    {
        MyLinkedList<String> entries = new MyLinkedList<>();
        entries.addFirst("hello");
        entries.addFirst("world");
        entries.add(1, "programming");
        entries.addLast("Java");

        assertEquals("world", entries.getFirst());
        assertEquals("Java", entries.getLast());
        assertEquals("programming", entries.get(1));
        assertEquals(4, entries.size());

        assertEquals("programming", entries.remove(1));
        assertEquals("world", entries.removeFirst());
        assertEquals("Java", entries.removeLast());
        assertEquals(1, entries.size());
    }

    @Test
    public void testInvalidIndex()
    {
        list.addLast(10);
        list.addFirst(20);
        list.addLast(55);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, 56));
    }

    @Test
    public void testGetFirstAndLastFromEmptyList()
    {
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }

    @Test
    public void testRemoveFirstAndLastFromEmptyList()
    {
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }
}
