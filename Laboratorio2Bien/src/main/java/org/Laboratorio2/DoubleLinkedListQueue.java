package org.Laboratorio2;

import java.util.Comparator;

public class DoubleLinkedListQueue<T> implements DoubleEndedQueue<T>{

    private DequeNode<T> first;
    private DequeNode<T> last;

    public DoubleLinkedListQueue() {
        first = null;
        last = null;
    }

    public DoubleLinkedListQueue(DoubleLinkedListQueue<T> list) {
        DequeNode<T> aux = list.peekFirst();

        while(aux != null) {
            DequeNode<T> node = new DequeNode<>(aux.getItem(),null,null);
            this.append(node);
            aux = aux.getNext();
        }

    }

    @Override
    public void append(DequeNode<T> node) {
        if(node == null) {
            throw new RuntimeException("Append : Can not add a not existing node");
        }
        if(!nodeIsInList(node)){
            node.setNext(null);
            if(first == null) {
                first = node;
                node.setPrevious(null);
            } else {
                node.setPrevious(last);
                last.setNext(node);
            }
            last = node;
        }
    }

    @Override
    public void appendLeft(DequeNode<T> node) {

        if(node == null) {
            throw new RuntimeException("Append : Can not add a not existing node");
        }

        if(!nodeIsInList(node)){
            node.setPrevious(null);
            if(first == null) {
                node.setNext(null);
                first = node;
                last = node;
            } else {
                first.setPrevious(node);
                node.setNext(first);
                first = node;
            }
        }
    }

    @Override
    public void deleteFirst() {
        if(first == null) {
            throw new RuntimeException("Empty list : Can not delete first");
        }

        if(this.size() == 1) {
            first = null;
            last = null;
        } else {
            DequeNode<T> aux = first.getNext();
            aux.setPrevious(null);
            first = aux;
        }

    }

    @Override
    public void deleteLast() {
        if(first == null) {
            throw new RuntimeException("Empty list : Can not delete last");
        }

        if(this.size() == 1) {
            first = null;
            last = null;

        } else {
            DequeNode<T> aux = last.getPrevious();
            aux.setNext(null);
            last = aux;
        }
    }

    @Override
    public DequeNode<T> peekFirst() {
        return first;
    }

    @Override
    public DequeNode<T> peekLast() {
        return last;
    }

    @Override
    public int size() {
        DequeNode<T> aux = first;
        int sz = 0;

        while(aux != null) {
            aux = aux.getNext();
            sz++;
        }
        return sz;
    }

    //GetAt lanzará una excepción si la posición no esta en el rango 0 a size()-1
    @Override
    public DequeNode<T> getAt(int position) {
        if(position < 0){
            throw new RuntimeException("Negative index on getAt(): " + position);
        }else if(position > this.size()-1){
            throw new RuntimeException("Out of bounds index on getAt(): " + position + " because size is " + this.size());
        }

        DequeNode<T> aux = first;
        int i = 0;

        while(i<position) {
            aux = aux.getNext();
            i++;
        }

        return aux;
    }

    //Find devolverá null si el item no se encuentra en la lista
    @Override
    public DequeNode<T> find(T item) {
        DequeNode<T> aux = first;

        while(aux != null && !aux.getItem().equals(item)) {
            aux = aux.getNext();
        }
        return aux;
    }

    //Delete no tendrá ningún efecto si el nodo no está en la lista, así como si esta está vacía
    @Override
    public void delete(DequeNode<T> node) {
        if(nodeIsInList(node)){
            if(node.isFirstNode()){
                deleteFirst();
            }else if(node.isLastNode()){
                deleteLast();
            }else{
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
            }
        }
    }

    private boolean nodeIsInList(DequeNode<T> node){
        DequeNode<T> aux = first;
        boolean isIn = false;
        while(aux != null && !isIn){
            if(aux.equals(node)){
                isIn = true;
            }
            aux = aux.getNext();
        }
        return isIn;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        if (this.size() > 1) {
            for (int limit = this.size() - 1; limit > 0; limit--) {
                for (int firstIndex = 0; firstIndex < limit; firstIndex++) {
                    int secondIndex = firstIndex + 1;
                    if (comparator.compare(this.getAt(firstIndex).getItem(),this.getAt(secondIndex).getItem()) > 0) {
                        changeNodes(this.getAt(firstIndex),this.getAt(secondIndex));
                    }
                }
            }
        }
    }

    private void changeNodes(DequeNode<T> node1,DequeNode<T> node2){

        if(!node1.isFirstNode()){
            node1.getPrevious().setNext(node2);
        }else{
            first = node2;
        }

        if(!node2.isLastNode()){
            node2.getNext().setPrevious(node1);
        }else{
            last = node1;
        }

        node2.setPrevious(node1.getPrevious());
        node1.setNext(node2.getNext());
        node1.setPrevious(node2);
        node2.setNext(node1);

    }


}
