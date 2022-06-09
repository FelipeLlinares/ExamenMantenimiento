package org.Laboratorio2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeNodeTest {

    @BeforeEach


    @Test
    public void getItemShouldReturnNodeItem(){
        DequeNode<Integer> node = new DequeNode<>(8,null,null);
        int expectedValue = 8;
        int obtainedValue = node.getItem();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void getNextShouldReturnNodeNext(){
        DequeNode<Integer> next = new DequeNode<>(10,null,null);
        DequeNode<Integer> node = new DequeNode<>(8,next,null);
        DequeNode<Integer> obtainedValue = node.getNext();
        assertEquals(next,obtainedValue);
    }

    @Test
    public void getPreviousShouldReturnNodePrevious(){
        DequeNode<Integer> previous = new DequeNode<>(5,null,null);
        DequeNode<Integer> node = new DequeNode<>(8,null,previous);
        DequeNode<Integer> obtainedValue = node.getPrevious();
        assertEquals(previous,obtainedValue);
    }

    @Test
    public void setItemShouldChangeItem(){
        DequeNode<Integer> node = new DequeNode<>(8,null,null);
        node.setItem(10);
        int expectedValue = 10;
        int obtainedValue = node.getItem();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void setNextShouldChangeNextNode(){
        DequeNode<Integer> node = new DequeNode<>(8,null,null);
        DequeNode<Integer> expectedValue = new DequeNode<>(7,null,null);
        node.setNext(expectedValue);
        DequeNode<Integer> obtainedValue = node.getNext();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void setPreviousShouldChangePreviousNode(){
        DequeNode<Integer> node = new DequeNode<>(8,null,null);
        DequeNode<Integer> expectedValue = new DequeNode<>(7,null,null);
        node.setPrevious(expectedValue);
        DequeNode<Integer> obtainedValue = node.getPrevious();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isFirstNodeShouldReturnTrueWhenPreviousNull(){
        DequeNode<Integer> node = new DequeNode<>(8,null,null);
        boolean expectedValue = true;
        boolean obtainedValue = node.isFirstNode();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isFirstNodeShouldReturnFalseWhenPreviousNotNull(){
        DequeNode<Integer> previous = new DequeNode<>(5,null,null);
        DequeNode<Integer> node = new DequeNode<>(8,null,previous);
        boolean expectedValue = false;
        boolean obtainedValue = node.isFirstNode();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isLastNodeShouldReturnTrueWhenNextNull(){
        DequeNode<Integer> node = new DequeNode<>(8,null,null);
        boolean expectedValue = true;
        boolean obtainedValue = node.isLastNode();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isLastNodeShouldReturnFalseWhenNextNotNull(){
        DequeNode<Integer> next = new DequeNode<>(5,null,null);
        DequeNode<Integer> node = new DequeNode<>(8,next,null);
        boolean expectedValue = false;
        boolean obtainedValue = node.isLastNode();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isNotATerminalNodeShouldReturnTrueWhenNextAndPreviousNotNull(){
        DequeNode<Integer> next = new DequeNode<>(5,null,null);
        DequeNode<Integer> previous = new DequeNode<>(7,null,null);
        DequeNode<Integer> node = new DequeNode<>(8,next,previous);
        boolean expectedValue = true;
        boolean obtainedValue = node.isNotATerminalNode();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isNotATerminalNodeShouldReturnFalseWhenNextNull(){
        DequeNode<Integer> previous = new DequeNode<>(7,null,null);
        DequeNode<Integer> node = new DequeNode<>(8,null,previous);
        boolean expectedValue = false;
        boolean obtainedValue = node.isNotATerminalNode();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isNotATerminalNodeShouldReturnFalseWhenPreviousNull(){
        DequeNode<Integer> next = new DequeNode<>(5,null,null);
        DequeNode<Integer> node = new DequeNode<>(8,next,null);
        boolean expectedValue = false;
        boolean obtainedValue = node.isNotATerminalNode();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void isNotATerminalNodeShouldReturnFalseWhenNextAndPreviousNull(){
        DequeNode<Integer> node = new DequeNode<>(8,null,null);
        boolean expectedValue = false;
        boolean obtainedValue = node.isNotATerminalNode();
        assertEquals(expectedValue,obtainedValue);
    }


}