package org.Laboratorio2;

import java.util.Comparator;

public interface DoubleEndedQueue<T> {
    // Basic operations
    void append(DequeNode<T> node) ;
    void appendLeft(DequeNode<T> node) ;
    void deleteFirst() ;
    void deleteLast() ;
    DequeNode<T> peekFirst() ;
    DequeNode<T> peekLast() ;
    int size() ;

    // Complex operations
    DequeNode<T> getAt(int position) ;
    DequeNode<T> find (T item) ;
    void delete(DequeNode<T> node) ;
    void sort(Comparator<T> comparator) ;
}
