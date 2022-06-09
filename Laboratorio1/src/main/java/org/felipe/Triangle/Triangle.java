package org.felipe.Triangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class providing a getType() method that, given the length of the sides of a triangle,
 * returns its type: equilateral, isosceles, or scalene
 *
 * @author Felipe Llinares
 */
public class Triangle {

  public enum TriangleType {
    SCALENE,
    ISOSCELES,
    EQUILATERAL;
  }

  public TriangleType getType(double side1, double side2, double side3) {
    TriangleType result;

    // IMPLEMENTATION 1
    if (((side1 == 0) || (side2 == 0)) || (side3 == 0)) {
      throw new RuntimeException("At least one side has length 0");
    }

    if (((side1 < 0) || (side2 < 0)) || (side3 < 0)) {
      throw new RuntimeException("At least one side has a negative length");
    }

    if ((side1 + side2 <= side3) || (side1 + side3 <= side2) || (side2 + side3 <= side1)) {
      throw new RuntimeException("The side lengths do not correspond to a valid triangle");
    }

    if (side1 == side2) {
      if (side2 == side3) {
        result = TriangleType.EQUILATERAL;
      } else {
        result = TriangleType.ISOSCELES;
      }
    } else {
      if (side2 != side3) {
        if (side1 != side3) {
          result = TriangleType.SCALENE;
        } else {
          return TriangleType.ISOSCELES;
        }
      } else {
        result = TriangleType.ISOSCELES;
      }
    }

    /*
    // IMPLEMENTATION 2
    var sides = new ArrayList<>(List.of(side1, side2, side3));
    Collections.sort(sides);

    if (sides.stream().anyMatch(i -> i == 0.0)) {
      throw new RuntimeException("One size has length 0");
    }

    if (sides.stream().anyMatch(i -> i < 0.0)) {
      throw new RuntimeException("One size has a negative length");
    }

    if ((side1 + side2 <= side3) || (side1 + side3 <= side2) || (side2 + side3 <= side1)) {
      throw new RuntimeException("The side lengths do not correspond to a valid triangle") ;
    }

    var distinctValues = sides.stream().distinct().count();

    switch ((int) distinctValues) {
      case 3:
        result = TriangleType.SCALENE;
        break;
      case 1:
        result =  TriangleType.EQUILATERAL;
        break;
      default:
        result = TriangleType.ISOSCELES;
        break;
    };
  */
    return result;
  }
}
