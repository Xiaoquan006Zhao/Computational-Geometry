//Author: Xiaoquan Zhao

/**
 * Computing convex hull in 2d space by implementing Graham Scan. The essence of this algorithm is first sort the input by 
 * polar angle wrt. to a selected point. Then apply the insight that a segment is part of the CH iff all points lie on one
 * side. To check if a point lie on the right side or the left side, one can use cross product.
 *
 * This algorithm takes O (n logn) dominated by the pre-sorting. 
 */

public class GrahamScan {
  
  
  public GrahamScan () {
    

}
