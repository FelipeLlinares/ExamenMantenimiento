package org.Laboratorio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListQueueTest {

    private DoubleLinkedListQueue<Integer> list;

    @BeforeEach
    public void setup() {
        list = new DoubleLinkedListQueue<>();
    }


    //Tests de append
    @Test
    public void appendShouldThrowExceptionWheNullNode() {
        assertThrows(RuntimeException.class, () -> list.append(null));
    }

    @Test
    public void appendShouldSetNewNodeAsTheFirstAndLastWhenEmptyList() {
        DequeNode<Integer> expectedValue = new DequeNode<>(1, null, null);
        list.append(expectedValue);
        DequeNode<Integer> obtainedValue = list.peekFirst();
        assertEquals(expectedValue, obtainedValue);
        obtainedValue = list.peekLast();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void appendShouldSetNewNodeAsTheLastOneWhenListSize1() {
        DequeNode<Integer> expectedValue = new DequeNode<>(2, null, null);
        list.append(new DequeNode<>(1, null, null));
        list.append(expectedValue);
        DequeNode<Integer> obtainedValue = list.peekLast();
        assertEquals(expectedValue, obtainedValue);
        assertEquals(expectedValue,list.peekFirst().getNext());
    }

    @Test
    public void appendShouldSetNewNodeAsTheLastOne() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(3, null, null));
        list.append(new DequeNode<>(4, null, null));

        DequeNode<Integer> expectedValue = new DequeNode<>(5, null, null);
        list.append(expectedValue);

        DequeNode<Integer> obtainedValue = list.peekLast();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void appendShouldDoNothingIfNodeIsThere(){
        DequeNode<Integer> node = new DequeNode<>(1,null,null);
        list.append(node);
        list.append(node);
        list.append(node);
        list.append(node);
        DequeNode<Integer> obtainedValue = list.peekLast();
        assertEquals(node, obtainedValue);
        obtainedValue = list.peekFirst();
        assertEquals(node, obtainedValue);
    }

    @Test
    public void appendNodeWithReferencesToNextAndPreviousToEmptyListIgnoresReferences(){
        DequeNode<Integer> next = new DequeNode<>(2, null, null);
        DequeNode<Integer> previous = new DequeNode<>(2, null, null);
        list.append(new DequeNode<>(1, next, previous));

        assertEquals(list.peekFirst(),list.peekLast());
        assertNull(list.peekFirst().getPrevious());
        assertNull(list.peekLast().getNext());
    }

    @Test
    public void appendNodeWithReferencesToNextAndPreviousToListIgnoresReferences(){
        list.append(new DequeNode<>(8,null,null));
        DequeNode<Integer> previous = new DequeNode<>(5,null,null);
        list.append(previous);

        DequeNode<Integer> node = (new DequeNode<>(1, new DequeNode<>(2, null, null), new DequeNode<>(2, null, null)));
        list.append(node);

        DequeNode<Integer> next = new DequeNode<>(7,null,null);
        list.append(next);
        list.append(new DequeNode<>(10,null,null));

        assertEquals(node.getPrevious(),previous);
        assertEquals(node.getNext(),next);
        assertEquals(5,list.size());

    }

    //Tests de appendLeft
    @Test
    public void appendLeftShouldThrowExceptionWhenNullNode() {
        assertThrows(RuntimeException.class, () -> list.appendLeft(null));
    }

    @Test
    public void appendLeftShouldSetNewNodeAsTheFirstAndLastWhenEmptyList() {
        DequeNode<Integer> expectedValue = new DequeNode<>(1, null, null);
        list.appendLeft(expectedValue);
        DequeNode<Integer> obtainedValue = list.peekLast();
        assertEquals(expectedValue, obtainedValue);
        obtainedValue = list.peekFirst();
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void appendLeftShouldSetNewNodeAsTheFirstOneWhenListSize1() {
        DequeNode<Integer> expectedValue = new DequeNode<>(2, null, null);
        list.appendLeft(new DequeNode<>(1, null, null));
        list.appendLeft(expectedValue);
        DequeNode<Integer> obtainedValue = list.peekFirst();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void appendLeftShouldSetNewNodeAsTheFirstOne() {
        list.appendLeft(new DequeNode<>(1, null, null));
        list.appendLeft(new DequeNode<>(2, null, null));
        list.appendLeft(new DequeNode<>(3, null, null));
        list.appendLeft(new DequeNode<>(4, null, null));
        DequeNode<Integer> expectedValue = new DequeNode<>(5, null, null);
        list.appendLeft(expectedValue);
        DequeNode<Integer> obtainedValue = list.peekFirst();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void appendLeftShouldDoNothingIfNodeIsThere(){
        DequeNode<Integer> expectedValue = new DequeNode<>(1,null,null);
        list.appendLeft(expectedValue);
        list.appendLeft(expectedValue);
        list.appendLeft(expectedValue);
        list.appendLeft(expectedValue);
        DequeNode<Integer> obtainedValue = list.peekLast();
        assertEquals(expectedValue, obtainedValue);
        obtainedValue = list.peekFirst();
        assertEquals(expectedValue,obtainedValue);
        assertEquals(1,list.size());
    }

    @Test
    public void appendLeftNodeWithReferencesToNextAndPreviousToEmptyListIgnoresReferences(){
        DequeNode<Integer> next = new DequeNode<>(2, null, null);
        DequeNode<Integer> previous = new DequeNode<>(2, null, null);
        list.appendLeft(new DequeNode<>(1, next, previous));

        assertEquals(list.peekFirst(),list.peekLast());
        assertNull(list.peekFirst().getPrevious());
        assertNull(list.peekLast().getNext());
    }

    @Test
    public void appendLeftNodeWithReferencesToNextAndPreviousToListIgnoresReferences(){
        list.append(new DequeNode<>(8,null,null));
        DequeNode<Integer> next = new DequeNode<>(5,null,null);
        list.appendLeft(next);

        DequeNode<Integer> node = (new DequeNode<>(1, new DequeNode<>(2, null, null), new DequeNode<>(2, null, null)));
        list.appendLeft(node);

        DequeNode<Integer> previous = new DequeNode<>(7,null,null);
        list.appendLeft(previous);
        list.appendLeft(new DequeNode<>(10,null,null));

        assertEquals(node.getPrevious(),previous);
        assertEquals(node.getNext(),next);
        assertEquals(5,list.size());

    }


    //Tests de deleteFirst
    @Test
    public void deleteFirstShouldThrowAnExceptionIfListIsEmpty() {
        assertThrows(RuntimeException.class, () -> list.deleteFirst());
    }

    @Test
    public void deleteFirstShouldSetFirstAndLastAsNullWhenThereIsOnlyOneNode() {
        list.append(new DequeNode<>(1, null, null));
        list.deleteFirst();
        assertNull(list.peekFirst());
        assertNull(list.peekLast());
    }


    @Test
    public void deleteFirstShouldSetSecondNodeAsTheFirstOne() {
        list.append(new DequeNode<>(1, null, null));
        DequeNode<Integer> expectedValue = new DequeNode<>(2, null, null);
        list.append(expectedValue);
        list.deleteFirst();
        DequeNode<Integer> obtainedValue = list.peekFirst();
        assertEquals(expectedValue, obtainedValue);
    }


    //Tests de DeleteLast
    @Test
    public void deleteLastShouldThrowAnExceptionIfListIsEmpty() {
        assertThrows(RuntimeException.class, () -> list.deleteLast());
    }

    @Test
    public void deleteLastShouldSetFirstAndLastAsNullWhenThereIsOnlyOneNode() {
        list.append(new DequeNode<>(1, null, null));
        list.deleteLast();
        assertNull(list.peekFirst());
        assertNull(list.peekLast());
    }


    @Test
    public void deleteLastShouldSetSecondToLastNodeAsTheLastOne() {
        DequeNode<Integer> expectedValue = new DequeNode<>(1, null, null);
        list.append(expectedValue);
        list.append(new DequeNode<>(2, null, null));
        list.deleteLast();
        DequeNode<Integer> obtainedValue = list.peekLast();
        assertEquals(expectedValue, obtainedValue);
    }

    //Tests de size
    @Test
    public void sizeShouldReturn0WhenEmptyList() {
        int expectedValue = 0;
        int obtainedValue = list.size();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void sizeShouldReturn1WhenOneNodeInTheList() {
        int expectedValue = 1;
        list.append(new DequeNode<>(1, null, null));
        int obtainedValue = list.size();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void sizeShouldReturn2WhenTwoNodesAppendedToTheList() {
        int expectedValue = 2;
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        int obtainedValue = list.size();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void sizeShouldReturn5WhenFiveNodesAppendedToTheList() {
        int expectedValue = 5;
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(3, null, null));
        list.append(new DequeNode<>(4, null, null));
        list.append(new DequeNode<>(5, null, null));
        int obtainedValue = list.size();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void sizeShouldIncrementByOneWhenAppendNode() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(3, null, null));
        list.append(new DequeNode<>(4, null, null));
        int expectedValue = list.size() + 1;
        list.append(new DequeNode<>(5, null, null));
        int obtainedValue = list.size();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void sizeShouldIncrementByOneWhenAppendLeftNode() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(3, null, null));
        list.append(new DequeNode<>(4, null, null));
        int expectedValue = list.size() + 1;
        list.appendLeft(new DequeNode<>(5, null, null));
        int obtainedValue = list.size();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    public void sizeShouldDecrementByOneWhenDeleteNode() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        list.append(node1);
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(3, null, null));
        list.append(new DequeNode<>(4, null, null));
        int expectedValue = list.size() - 1;
        list.delete(node1);
        int obtainedValue = list.size();
        assertEquals(expectedValue, obtainedValue);
    }

    //Tests de getAt
    @Test
    public void getAtShouldThrowExceptionWhenNegativePosition() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        assertThrows(RuntimeException.class, () -> list.getAt(-2));
    }

    @Test
    public void getAtShouldThrowExceptionWhenOutOfBoundsPosition() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        assertThrows(RuntimeException.class, () -> list.getAt(3));
    }

    @Test
    public void getAtShouldReturnFirstNodeAtPosGiven0() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        DequeNode<Integer> expectedValue = list.peekFirst();
        DequeNode<Integer> obtainedValue = list.getAt(0);
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void getAtShouldReturnLastNodeAtPosGivenSizeMinus1() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        DequeNode<Integer> expectedValue = list.peekLast();
        DequeNode<Integer> obtainedValue = list.getAt(list.size()-1);
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void getAtShouldReturnDequeNodeAtPosGiven() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        DequeNode<Integer> expectedValue = new DequeNode<>(6, null, null);
        list.append(expectedValue);
        list.append(new DequeNode<>(8, null, null));
        DequeNode<Integer> obtainedValue = list.getAt(2);
        assertEquals(expectedValue,obtainedValue);
    }


    //Tests de find
    @Test
    public void findShouldReturnNullWhenItemNotThere() {
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        DequeNode<Integer> obtainedValue = list.find(9);
        assertNull(obtainedValue);
    }

    @Test
    public void findShouldReturnFirstNodeWhenItemGivenIsInFirst(){
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        DequeNode<Integer> obtainedValue = list.find(1);
        assertEquals(list.peekFirst(),obtainedValue);
    }

    @Test
    public void findShouldReturnLastNodeWhenItemGivenIsInLast(){
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        DequeNode<Integer> obtainedValue = list.find(8);
        assertEquals(list.peekLast(),obtainedValue);
    }

    @Test
    public void findShouldReturnNodeWithItemGiven(){
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        DequeNode<Integer> expectedValue = new DequeNode<>(6, null, null);
        list.append(expectedValue);
        list.append(new DequeNode<>(8, null, null));
        DequeNode<Integer> obtainedValue = list.find(6);
        assertEquals(expectedValue,obtainedValue);
    }

    @Test
    public void findShouldReturnFirstNodeWithItemGiven(){
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        DequeNode<Integer> expectedValue = new DequeNode<>(6, null, null);
        list.append(expectedValue);
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        list.append(new DequeNode<>(6, null, null));
        DequeNode<Integer> obtainedValue = list.find(6);
        assertEquals(expectedValue,obtainedValue);
    }


    //Tests de delete
    @Test
    public void deleteShouldDoNothingIfNodeIsNotInList(){
        list.append(new DequeNode<>(1, null, null));
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        DoubleLinkedListQueue<Integer> aux = new DoubleLinkedListQueue<>(list);

        list.delete(new DequeNode<>(4,null,null));

        for(int i=0;i<list.size();i++){
            assertEquals(list.getAt(i).getItem(),aux.getAt(i).getItem());
        }
    }

    @Test
    public void deleteShouldDeleteFirstNodeWhenNodeGivenIsFirstOne(){
        DequeNode<Integer> node = new DequeNode<>(1, null, null);
        list.append(node);
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        DoubleLinkedListQueue<Integer> aux = new DoubleLinkedListQueue<>(list);
        list.delete(node);

        assertNotEquals(list.peekFirst(),node);
        for(int i=0;i<list.size();i++){
            assertEquals(list.getAt(i).getItem(),aux.getAt(i+1).getItem());
        }
        assertEquals(list.size(),aux.size()-1);
    }

    @Test
    public void deleteShouldDeleteLastNodeWhenNodeGivenIsLastOne(){
        DequeNode<Integer> node = new DequeNode<>(1, null, null);
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(new DequeNode<>(8, null, null));
        list.append(node);

        DoubleLinkedListQueue<Integer> aux = new DoubleLinkedListQueue<>(list);
        list.delete(node);

        assertNotEquals(list.peekLast(),node);

        for(int i=0;i<list.size();i++){
            assertEquals(list.getAt(i).getItem(),aux.getAt(i).getItem());
        }
        assertEquals(list.size(),aux.size()-1);
    }

    @Test
    public void deleteShouldDeleteNodeNodeGiven(){
        DequeNode<Integer> node = new DequeNode<>(1, null, null);
        list.append(new DequeNode<>(2, null, null));
        list.append(new DequeNode<>(6, null, null));
        list.append(node);
        list.append(new DequeNode<>(8, null, null));

        DoubleLinkedListQueue<Integer> aux = new DoubleLinkedListQueue<>(list);
        list.delete(node);

        for(int i=0;i<2;i++){
            assertEquals(list.getAt(i).getItem(),aux.getAt(i).getItem());
        }
        assertEquals(list.getAt(2).getItem(),aux.getAt(3).getItem());
        assertEquals(list.size(),aux.size()-1);
    }

    //Tests de sort
    @Test
    public void sortShouldDoNothingIfEmptyList(){
        list.sort(Comparator.comparingInt(o -> o));

        assertNull(list.peekFirst());
    }
    @Test
    public void sortShouldDoNothingIfListSizeIsOne(){
        DequeNode<Integer> node = new DequeNode<>(1,null,null);
        list.append(node);
        list.sort(Comparator.comparingInt(o -> o));

        assertEquals(list.peekFirst(),node);
        assertEquals(list.peekLast(),node);
    }

    @Test
    public void sortShouldSortListSizeTwoByComparatorParameter(){
        DequeNode<Integer> node1 = new DequeNode<>(1,null,null);
        DequeNode<Integer> node2 = new DequeNode<>(4,null,null);

        list.append(node2);
        list.append(node1);

        list.sort(Comparator.comparingInt(o -> o));

        assertEquals(node1,list.peekFirst());
        assertEquals(node2,list.peekLast());
    }

    @Test
    public void sortShouldSortListByComparatorParameter(){
        DequeNode<Integer> node1 = new DequeNode<>(2,null,null);
        DequeNode<Integer> node2 = new DequeNode<>(4,null,null);
        DequeNode<Integer> node3 = new DequeNode<>(6,null,null);
        DequeNode<Integer> node4 = new DequeNode<>(8,null,null);

        list.append(node3);
        list.append(node2);
        list.append(node4);
        list.append(node1);

        DoubleLinkedListQueue<Integer> aux = new DoubleLinkedListQueue<>(list);


        list.sort(Comparator.comparingInt(o -> o));

        int[] array = new int[]{3,1,0,2};

        for(int i=0;i<list.size();i++){
            assertEquals(list.getAt(i).getItem(),aux.getAt(array[i]).getItem());
        }
    }

    @Test
    public void sortShouldSortListByComparatorParameter2(){
        DequeNode<String> node1 = new DequeNode<>("1",null,null);
        DequeNode<String> node2 = new DequeNode<>("22",null,null);
        DequeNode<String> node3 = new DequeNode<>("333",null,null);
        DequeNode<String> node4 = new DequeNode<>("4444",null,null);
        DequeNode<String> node5 = new DequeNode<>("55555",null,null);
        DequeNode<String> node6 = new DequeNode<>("666666",null,null);

        DoubleLinkedListQueue<String> lista = new DoubleLinkedListQueue<>();
        lista.append(node2);
        lista.append(node4);
        lista.append(node1);
        lista.append(node6);
        lista.append(node5);
        lista.append(node3);

        DoubleLinkedListQueue<String> aux = new DoubleLinkedListQueue<>(lista);


        lista.sort(Comparator.comparingInt(String::length));

        int[] array = new int[]{2,0,5,1,4,3};

        for(int i=0;i<lista.size();i++)
            assertEquals(lista.getAt(i).getItem(), aux.getAt(array[i]).getItem());
    }
}