import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

class BoundedQueueTest {
    @Test
    public void shouldConstructorInitializeAllVariables(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(10);

        assertThat(ReflectionTestUtils.getField(bq,"capacity")).isEqualTo(10);
        assertThat(ReflectionTestUtils.getField(bq,"buffer")).isEqualTo(new ArrayList(10));
        assertThat(ReflectionTestUtils.getField(bq,"nextItem")).isEqualTo(0);
        assertThat(ReflectionTestUtils.getField(bq,"nextFreeSlot")).isEqualTo(0);
        assertThat(ReflectionTestUtils.getField(bq,"numberOfItems")).isEqualTo(0);
    }

    @Test
    public void shouldPutInsertItemWhenNumerOfItemsIsZero(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(10);

        ArrayList<Integer> buffer = new ArrayList(10);
        buffer.add(7);

        bq.put(7);

        assertThat(ReflectionTestUtils.getField(bq,"capacity")).isEqualTo(10);
        assertThat(ReflectionTestUtils.getField(bq,"buffer")).isEqualTo(buffer);
        assertThat(ReflectionTestUtils.getField(bq,"nextItem")).isEqualTo(0);
        assertThat(ReflectionTestUtils.getField(bq,"nextFreeSlot")).isEqualTo(1);
        assertThat(ReflectionTestUtils.getField(bq,"numberOfItems")).isEqualTo(1);
    }

    @Test
    public void shouldPutThrowExcepcionWhenNumerOfItemsEqualsCapacity(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(1);
        bq.put(7);
        assertThatThrownBy(() -> bq.put(9)).isInstanceOf(FullQueueException.class);
    }

    @Test
    public void shouldPutInsertItemWhenNumerOfItemsLessThanCapacity(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(10);

        ArrayList<Integer> buffer = new ArrayList(10);
        buffer.add(7);
        buffer.add(9);
        buffer.add(10);

        bq.put(7);
        bq.put(9);
        bq.put(10);

        assertThat(ReflectionTestUtils.getField(bq,"capacity")).isEqualTo(10);
        assertThat(ReflectionTestUtils.getField(bq,"buffer")).isEqualTo(buffer);
        assertThat(ReflectionTestUtils.getField(bq,"nextItem")).isEqualTo(0);
        assertThat(ReflectionTestUtils.getField(bq,"nextFreeSlot")).isEqualTo(3);
        assertThat(ReflectionTestUtils.getField(bq,"numberOfItems")).isEqualTo(3);
    }

    @Test
    public void shouldGetThrowExcepcionWhenNumerOfItemsEqualsZero(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(5);
        assertThatThrownBy(() -> bq.get()).isInstanceOf(EmptyQueueException.class);
    }

    @Test
    public void shouldGetTakeFirstItemWhenNumberOfItemsNotZero(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(10);

        ArrayList<Integer> buffer = new ArrayList(10);
        buffer.add(7);
        buffer.add(9);
        buffer.add(10);

        bq.put(7);
        bq.put(9);
        bq.put(10);

        bq.get();

        assertThat(ReflectionTestUtils.getField(bq,"capacity")).isEqualTo(10);
        assertThat(ReflectionTestUtils.getField(bq,"buffer")).isEqualTo(buffer);
        assertThat(ReflectionTestUtils.getField(bq,"nextItem")).isEqualTo(1);
        assertThat(ReflectionTestUtils.getField(bq,"nextFreeSlot")).isEqualTo(3);
        assertThat(ReflectionTestUtils.getField(bq,"numberOfItems")).isEqualTo(2);
    }

    @Test
    public void shouldPutInsertItemInFirstPositionAfterGet(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(5);

        ArrayList<Integer> buffer = new ArrayList(5);
        buffer.add(3);
        buffer.add(9);
        buffer.add(10);
        buffer.add(2);
        buffer.add(5);

        bq.put(7);
        bq.put(9);
        bq.put(10);
        bq.put(2);
        bq.get();
        bq.put(5);
        bq.put(3);

        assertThat(ReflectionTestUtils.getField(bq,"capacity")).isEqualTo(5);
        assertThat(ReflectionTestUtils.getField(bq,"buffer")).isEqualTo(buffer);
        assertThat(ReflectionTestUtils.getField(bq,"nextItem")).isEqualTo(1);
        assertThat(ReflectionTestUtils.getField(bq,"nextFreeSlot")).isEqualTo(1);
        assertThat(ReflectionTestUtils.getField(bq,"numberOfItems")).isEqualTo(5);
    }

    @Test
    public void shouldGetTakeItemsInFirstPositions(){
        BoundedQueue<Integer> bq = new BoundedQueue<>(5);

        ArrayList<Integer> buffer = new ArrayList(5);
        buffer.add(3);
        buffer.add(4);
        buffer.add(6);
        buffer.add(2);
        buffer.add(5);

        bq.put(7);
        bq.put(9);
        bq.put(10);
        bq.put(2);
        bq.put(5);

        bq.get();
        bq.get();
        bq.get();

        bq.put(3);
        bq.put(4);
        bq.put(6);
        bq.get();

        assertThat(ReflectionTestUtils.getField(bq,"capacity")).isEqualTo(5);
        assertThat(ReflectionTestUtils.getField(bq,"buffer")).isEqualTo(buffer);
        assertThat(ReflectionTestUtils.getField(bq,"nextItem")).isEqualTo(4);
        assertThat(ReflectionTestUtils.getField(bq,"nextFreeSlot")).isEqualTo(3);
        assertThat(ReflectionTestUtils.getField(bq,"numberOfItems")).isEqualTo(4);
    }



}