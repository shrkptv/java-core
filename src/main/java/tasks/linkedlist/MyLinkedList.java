package tasks.linkedlist;

import java.util.NoSuchElementException;


public class MyLinkedList <T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public MyLinkedList() {
    }

    public int size()
    {
        return size;
    }

    public T getFirst()
    {
        if(head == null)
        {
            throw new NoSuchElementException();
        }

        return head.getInfo();
    }

    public T getLast()
    {
        if(tail == null)
        {
            throw new NoSuchElementException();
        }
        return tail.getInfo();
    }

    public void addFirst(T el)
    {
        if(size == 0)
        {
            head = new Node<>(el, null, null);
            tail = head;
        }
        else
        {
            Node<T> oldHead = head;
            head = new Node<>(el, null, oldHead);
            oldHead.setPrev(head);
        }
        size++;
    }

    public void addLast(T el)
    {
        if(size == 0)
        {
            addFirst(el);
        }
        else
        {
            Node<T> oldTail = tail;
            tail = new Node<>(el, oldTail, null);
            oldTail.setNext(tail);
            size++;
        }
    }

    private Node <T> findNode(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = head;
        for(int i = 0; i < index; i++)
        {
            node = node.getNext();
        }
        return node;
    }

    public T get(int index)
    {
        return (findNode(index)).getInfo();
    }

    public T removeFirst()
    {
        if(head == null)
        {
           throw new NoSuchElementException();
        }

        T value = head.getInfo();

        head = head.getNext();

        if(head == null)
        {
            tail = null;
        }
        else
        {
            head.setPrev(null);
        }

        size--;
        return value;
    }

    public T removeLast()
    {
        if(tail == null)
        {
            throw new NoSuchElementException();
        }

        T value = tail.getInfo();

        tail = tail.getPrev();
        if(tail == null)
        {
            head = null;
        }
        else
        {
            tail.setNext(null);
        }

        size--;
        return value;
    }

    public void add(int index, T el)
    {
        if(index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0)
        {
            addFirst(el);
            return;
        }
        else if(index == size)
        {
            addLast(el);
            return;
        }

        Node<T> nextEl = findNode(index);
        Node<T> prevEl = nextEl.getPrev();

        Node<T> newNode = new Node<>(el, prevEl, nextEl);
        nextEl.setPrev(newNode);
        prevEl.setNext(newNode);

        size++;
    }

    public T remove(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0)
        {
            return removeFirst();
        }
        else if (index == size - 1)
        {
            return removeLast();
        }

        Node<T> oldNode = findNode(index);
        T value = oldNode.getInfo();
        Node<T> prevNode = oldNode.getPrev();
        Node<T> nextNode = oldNode.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);

        size--;

        return value;
    }
}
