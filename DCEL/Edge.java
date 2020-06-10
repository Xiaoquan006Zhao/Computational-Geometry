
/***
 * This class is used to represent edge in DCEL.
 */
 
public class Edge {
	  public String name;
	  
	  private Vert start;//origin
	  private Vert end;
	  public Edge twin;
	  private Edge prev;
	  private Edge next;
	  
	  public int face;//the face that the edge bounds
	  
	  Edge(String name){
			this.name = name;
			start = null;
			end = null;
			prev = null;
			next = null;
	  }
	  
	  public Vert getStart() {
			 return start;
	  }
	  
	  public Vert getEnd() {
			 return end;
	  }
	  
	  public void setStart(Point start) {
			 this.start = start;
	  }
	  
	  public void setEnd(Point end) {
			 this.end = end;
	  }
	  
	  public Edge getPrev() {
			 return prev;
	  }
	  
	  public void setPrev(Edge prev) {
			 this.prev = prev;
	  }
	  
	  public Edge getNext() {
			 return next;
	  }
	  
	  public void setNext(Edge next) {
			 this.next = next;
	  }
	  
	  public void setEndPoint(Vert a) {
			 end = a;
	  }
	  
	  public void setStartPoint(Vert b) {
			 start = b;
	  }
	  
	  public String getFace() {
			 return "f" + face;
	  }
    
  }
