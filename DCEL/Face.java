
import java.util.ArrayList;

/***
 * This class is used to represent edge in DCEL.
 */
 
public class Face {
	  private int face;
	  private Edge outer;
	  private ArrayList<Edge> inner = new ArrayList<Edge>();
    
	  public Face (int face, Edge outer, ArrayList<Edge> inner){
			 this.face = face;
			 this.outer = outer;
			 this.inner = inner;
	  }
	  
	  public Edge getOuter(){
			 return outer;
	  }
    
	  public void setOuter(Edge outer){
			 this.outer = outer;
	  }
    
	  public ArrayList<Edge> getInner(){
			 return inner;
	  }
    
	  public void setInner(ArrayList<Edge> inner){
			 this.inner = inner;
	  }
	  
	  public void setFace(int face) {
			 this.face = face;
	  }
	  
	  public int getFace() {
			 return face;
	  }
	  
}
