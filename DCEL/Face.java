
import java.util.ArrayList;

/***
 * This class is used to represent edge in DCEL.
 */
 
public class Face {
    private Edge outer;
    private ArrayList<Edge> inner = new ArrayList<Edge>();
    
    public Face (Edge outer, ArrayList<Edge> inner){
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
}
