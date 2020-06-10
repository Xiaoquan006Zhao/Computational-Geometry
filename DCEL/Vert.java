
/***
 * This class is used to represent vertex in DCEL.
 */

public class Vert {
      public final double x;
      public final double y;
	  public final String name;
	  private Edge incidentEdge;
	  
	  Vert(String name, double x, double y){
			 this.x = x;
             this.y = y;
			 this.name = name;
			 incidentEdge = null;
	  }
	  
	  public Edge getIncident() {
			 return incidentEdge;
	  }
	  
	  public void setIncident(Edge e) {
			 incidentEdge = e;
	  }
	  
	  public String toString (){
			 return name + " (" + x + ", " + y + ") ";
	  }
}
