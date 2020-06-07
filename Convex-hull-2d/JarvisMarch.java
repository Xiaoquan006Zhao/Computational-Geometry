import java.util.*;

/**
 * Computing convex hull in 2d space by implementing Javis's March. The essence of this algorithm is first sort the input by 
 * polar angle wrt. to a selected point. Then exclude the point and continue the sorting and selection.
 *
 * This algorithm takes O (nh). The run time is output sensitive and h is the number of vertices in the convex hull.  
 * output represent by vertices of the convex hull in clockwise order.
 */

public class JavisMarch {
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
	    
	    private double length(point a, point b) {
	   		return Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
	    }
	    
	    private double cross(point a, point b, point reference) {
	   		double rax = a.x - reference.x;
	   		double ray = a.y - reference.y;
	   		//vector ab
	   		double abx = b.x - reference.x;
	   		double aby = b.y - reference.y;
	   		
	   		double z = rax * aby - ray * abx; //cross-product analogy in 2d
	   		
	   		return z;
	    }
	    
	    private int comparatorPolar(point a, point b, point reference) {
	   		//vector ra
	   		double z = cross(a, b, reference);
	   		
	   		if(z != 0) return (z > 0)? 1 : -1;
	   		else return Double.compare(length(a, reference), length(b, reference)); 
	   		//the vectors are colinear then order them by magnitude.
	    }
	    
	    //assuming the input points are all distinct.
	    @SuppressWarnings("unchecked")
	    public  JavisMarch (ArrayList<point> inputPoints) throws Exception {
	        if(inputPoints.size() == 0)
	            throw new Exception("empty input");
	        
	        
	        point initial = findReference(inputPoints);
	        
	        Collections.sort(inputPoints, (a, b) -> comparatorPolar(a, b, initial));
	        points = (ArrayList<point>) inputPoints.clone();
	        point holder = inputPoints.get(1);
	        ch.add(holder);
	        
	        while (ch.lastElement() != initial) {
	      		 point reference = holder;
	      		 Collections.sort(inputPoints, (a, b) -> comparatorPolar(a, b, reference));
	      		 holder = inputPoints.get(1);
	      		 ch.add(holder);
	        }
	        
	        
	        ch.remove(ch.size()-1);
	        ch.add(0, initial);
	        points = (ArrayList<point>)inputPoints.clone(); //deep copy of sorted input points; 
	    }
}