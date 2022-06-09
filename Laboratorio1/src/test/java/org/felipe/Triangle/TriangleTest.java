package org.felipe.Triangle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.felipe.Triangle.Triangle;

public class TriangleTest {
    private Triangle triangle;

    @BeforeEach
    public void setup(){ triangle = new Triangle(); }

    @AfterEach
    public void finish() { triangle = null; }

    @Test
    public void getTypeRaisesExceptionWhenSide1Zero(){
        assertThrows(RuntimeException.class, () -> triangle.getType(0,2,2));
    }

    @Test
    public void getTypeRaisesExceptionWhenSide2Zero(){
        assertThrows(RuntimeException.class, () -> triangle.getType(2,0,2));
    }

    @Test
    public void getTypeRaisesExceptionWhenSide3Zero(){
        assertThrows(RuntimeException.class, () -> triangle.getType(2,2,0));
    }

    @Test
    public void getTypeRaisesExceptionWhenSide1Negative(){
        assertThrows(RuntimeException.class, () -> triangle.getType(-1,2,2));
    }

    @Test
    public void getTypeRaisesExceptionWhenSide2Negative(){
        assertThrows(RuntimeException.class, () -> triangle.getType(2,-1,2));
    }

    @Test
    public void getTypeRaisesExceptionWhenSide3Negative(){
        assertThrows(RuntimeException.class, () -> triangle.getType(2,2,-1));
    }

    @Test
    public void getTypeRaisesExceptionWhenSide1Plus2SmallerOrEqualThanSide3(){
        assertThrows(RuntimeException.class, ()-> triangle.getType(2,1,4));
    }

    @Test
    public void getTypeRaisesExceptionWhenSide1Plus3SmallerOrEqualThanSide2(){
        assertThrows(RuntimeException.class, ()-> triangle.getType(2,5,2));
    }
    @Test
    public void getTypeRaisesExceptionWhenSide2Plus3SmallerOrEqualThanSide1(){
        assertThrows(RuntimeException.class, ()-> triangle.getType(8,3,4));
    }

    @Test
    public void getTypeReturnEquilateralWhenAllEqual(){
        Triangle.TriangleType expectedType = Triangle.TriangleType.EQUILATERAL;
        Triangle.TriangleType obtainedType = triangle.getType(3,3,3);
        assertEquals(expectedType,obtainedType);
    }

    @Test
    public void getTypeReturnScaleneWhenAllDiferent(){
        Triangle.TriangleType expectedType = Triangle.TriangleType.SCALENE;
        Triangle.TriangleType obtainedType = triangle.getType(3,4,2);
        assertEquals(expectedType,obtainedType);
    }

    @Test
    public void getTypeReturnIsoscelesWhenSide1And2Equal(){
        Triangle.TriangleType expectedType = Triangle.TriangleType.ISOSCELES;
        Triangle.TriangleType obtainedType = triangle.getType(4,4,2);
        assertEquals(expectedType,obtainedType);
    }

    @Test
    public void getTypeReturnIsoscelesWhenSide1And3Equal(){
        Triangle.TriangleType expectedType = Triangle.TriangleType.ISOSCELES;
        Triangle.TriangleType obtainedType = triangle.getType(2,1,2);
        assertEquals(expectedType,obtainedType);
    }

    @Test
    public void getTypeReturnIsoscelesWhenSide2And3Equal(){
        Triangle.TriangleType expectedType = Triangle.TriangleType.ISOSCELES;
        Triangle.TriangleType obtainedType = triangle.getType(2,3,3);
        assertEquals(expectedType,obtainedType);
    }

}
