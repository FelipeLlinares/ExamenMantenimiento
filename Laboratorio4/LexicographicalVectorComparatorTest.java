package org.uma.jmetal.util.comparator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uma.jmetal.util.errorchecking.exception.NullParameterException;

/**
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 * @version 1.0
 */
public class LexicographicalVectorComparatorTest {

  private LexicographicalVectorComparator comparator;

  @BeforeEach
  public void startup() {
    comparator = new LexicographicalVectorComparator();
  }

  @Test
  public void shouldFirstPointToCompareEqualsToNullRaiseAnException() {
    assertThrows(NullParameterException.class, () -> comparator.compare(null, new double[]{1, 2}));
  }

  @Test
  public void shouldSecondPointToCompareEqualsToNullRaiseAnException() {
    assertThrows(NullParameterException.class, () -> comparator.compare(new double[]{1, 2}, null));
  }

  @Test
  public void shouldCompareIdenticalPointsReturnZero() {
    assertEquals(0, comparator.compare(new double[]{1, 3}, new double[]{1, 3}));
  }

  @Test
  public void shouldCompareIdenticalPointsButTheFirstValueReturnPlus1() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0};
    double[] point2 = new double[]{-1.0, 0.0, 5.0, 7.0};

    assertEquals(1, comparator.compare(point1, point2));
  }

  @Test
  public void shouldCompareIdenticalPointsButTheFirstValueReturnMinus1() {
    double[] point1 = new double[]{-1.0, 0.0, 5.0, 7.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0, 7.0};

    assertEquals(-1, comparator.compare(point1, point2));
  }

  @Test
  public void shouldCompareIdenticalPointsButTheLastValueReturnMinus1() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 0.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0, 7.0};

    assertEquals(-1, comparator.compare(point1, point2));
  }

  @Test
  public void shouldCompareIdenticalPointsButTheLastValueReturnPlus1() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0, 0.0};

    assertEquals(1, comparator.compare(point1, point2));
  }

  @Test
  public void shouldCompareEmptyPointsReturnZero() {
    assertEquals(0, comparator.compare(new double[]{}, new double[]{}));
  }

  @Test
  public void shouldCompareDifferentLengthPointsWithTheSameValuesInTheCommonPositionsReturnZero() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0};

    assertEquals(0, comparator.compare(point1, point2));
  }


  //BUCLE WHILE
  //Saltar el bucle
  @Test
  public void shouldComputeSkipLoop(){
    assertEquals(0, comparator.compare(new double[]{}, new double[]{}));
  }

  //Pasar una vez
  @Test
  public void shouldComputeLoopIterateOnce() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0};
    double[] point2 = new double[]{1.0, 2.0, 5.0};

    assertEquals(-1, comparator.compare(point1, point2));
  }

  //Pasar dos veces
  @Test
  public void shouldComputeLoopIterateTwice() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0, 9.0};
    double[] point2 = new double[]{1.0, 0.0, 4.0, 7.0, 9.0};

    assertEquals(1, comparator.compare(point1, point2));
  }

  //Pasar M < N veces
  @Test
  public void shouldComputeLoopIterateMTimes() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0, 8.0};
    double[] point2 = new double[]{1.0, 0.0, 3.0, 7.0, 8.0};

    assertEquals(1, comparator.compare(point1, point2));
  }

  //Hacer N pasos
  @Test
  public void loopIteratesNTimes() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0, 6.0};

    assertEquals(1, comparator.compare(point1, point2));
  }

  //Hacer N-1 pasos
  @Test
  public void loopIteratesNMinusOneTimes() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 6.0, 7.0, 8.0, 11.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0, 6.0, 7.0, 9.0, 11.0};

    assertEquals(-1, comparator.compare(point1, point2));
  }

  //Hacer N+1 pasos
  @Test
  public void loopIteratesNPlusOneTimes() {
    double[] point1 = new double[]{1.0, 0.0, 5.0, 6.0, 7.0, 8.0, 11.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0, 6.0, 7.0, 8.0, 11.0};

    assertEquals(0, comparator.compare(point1, point2));
  }

  //IF-ELSE
  //Primera condición del if igual a TRUE
  @Test
  public void firstConditionIfTrue(){
    double[] point1 = new double[]{1.0, 0.0, 5.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0, 7.0};

    assertEquals(0, comparator.compare(point1, point2));
  }

  //Primera condición del if igual a FALSE y segunda a TRUE
  @Test
  public void firstConditionIfFalseSecondTrue(){
    double[] point1 = new double[]{1.0, 0.0, 5.0, 7.0};
    double[] point2 = new double[]{1.0, 0.0, 5.0};

    assertEquals(0, comparator.compare(point1, point2));
  }

  //Ambas condiciones del if FALSE -> entra al else
  @Test
  public void firstConditionIfFalseSecondFalse(){
    double[] point1 = new double[]{1.0, 0.0, 5.0, 6.0, 7.0, 8.0, 11.0};
    double[] point2 = new double[]{1.0, 0.0, 3.0, 6.0, 7.0, 8.0, 11.0};

    assertEquals(1, comparator.compare(point1, point2));
  }

}
