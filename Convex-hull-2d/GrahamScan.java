import java.util.*;

/**
 * Computing convex hull in 2d space by implementing Graham Scan. The essence of this algorithm is first sort the input by 
 * polar angle wrt. to a selected point. Then apply the insight that a segment is part of the CH iff all points lie on one
 * side. To check if a point lie on the right side or the left side, one can use cross product.
 *
 * This algorithm takes O (n logn) dominated by the pre-sorting. 
 */

public class GrahamScan {
    private ArrayList<point> points = new ArrayList<point>(); //should be sorted.
    private Stack<point> ch = new Stack<point>();
    
    //find the reference point that is used as the pivot for calculating polar angle. 
    //the lowest point (smallest y-coord), if ties, using the point with smallest x-coord.
    private point findReference (ArrayList<point> inputPoints){
        point reference = inputPoints.get(0);
        point cur = null;
        
        for(int i = 1; i < inputPoints.size(); i++){
            cur = inputPoints.get(i);
            if (cur.y < reference.y)
                reference = cur;
            else if (cur.y == reference.y && cur.x < reference.x)
                reference = cur;
        }
        
        return reference;
    }
    
    //assuming the input points are all distinct.
    public GrahamScan (ArrayList<point> inputPoints) {
        if(inputPoints.size == 0)
            throw new Exception("empty input");
        
        point reference = findReference(inputPoints);
        
        Collections.sort(inputPoints, )
    }

}
