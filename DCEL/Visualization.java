
import java.util.ArrayList;
import javafx.application.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.text.Text; 

/***
 * Using javafx to display the convex hull after calculation using either implementation.
 * One example is given below in main method.
 */

@SuppressWarnings("restriction")
public class Visualization extends Application { 
	private static ArrayList<Vert> verts = null; //points of CH that need to be displayed.
	private static ArrayList<Edge> edges = null;
	private static ArrayList<Face> faces = null;
	
	private static Color[] colors;
	
	// scene width and length
	private static double width = 600; //default = 600
	private static double length = 600; //default = 600
	
    // properties of the input decl
	static double xmax;
	static double ymax;
	static double xmin;
	static double ymin;	
	static point center;
	
	// initialize xmax, ymax, xmin, ymin from given inputs: verts
	private static void calculateProperties() {
		  xmax = verts.get(0).x;
		  ymax = verts.get(0).y;
		  xmin = verts.get(0).x;
		  ymin = verts.get(0).y;
		  
		  for(int i = 1; i < verts.size(); i++) {
				 point p = verts.get(i);
				 
				 if(p.x > xmax) 
						xmax = p.x;
				 else if(p.x < xmin) 
						xmin = p.x;
				 
				 if(p.y > ymax) 
						ymax = p.y;
				 else if(p.y < ymin) 
						ymin = p.y;
		  }
		  
		  center = new point((xmax+xmin)/2, (ymax+ymin)/2);
		  System.out.println("center: " + center.x + " ," + center.y);
	}
	
	// Synchronize input's coord with the view window coordinates.
    // the y coordinate is reversed. (keep this in mind if you want to modify the code).
	private static Vert offset(Vert p) {
		  double newX = 0;
		  double newY = 0;
		  double oldX = p.x;
		  double oldY = p.y;
			 
		  newX = (width/2 * 9/10) * Math.abs((oldX - center.x) / (xmax - center.x));
		  newY = (length/2 * 9/10) * Math.abs((oldY - center.y) / (ymax - center.y));
			 
		  newX = Double.isNaN(newX)? 0 : newX;
		  newY = Double.isNaN(newY)? 0 : newY;
		  
		  newX = (oldX < center.x)? -newX : newX;
		  newY = (oldY < center.y)? -newY : newY;
			 
		  newX += width/2;
		  newY += length/2;
		  newY = length - newY; 
		  
		  return new Vert(p.name, newX, newY);
	}
	
    // helper method used in loadVert to update coordinate.
	private static void offsetVert(ArrayList<Vert> inputs) {
		  for(int i = 0; i < inputs.size(); i++) { 
				 inputs.set(i, offset(inputs.get(i)));
		  }
	}
	
    // helper method used in loadEdge to update coordinates.
	private static void offsetEdge(ArrayList<Edge> inputs) {
		  for(int i = 0; i < inputs.size(); i++) {
				 Edge old = inputs.get(i);
				 
				 old.setStart(offset(old.getStart()));
				 old.setEnd(offset(old.getEnd()));
		  }
	}
	
    // should not be called independently. But one could
	public static void reload() {
		  offsetVert(verts);
		  offsetEdge(edges);
	}
	
    // load a dcel element.
	public static void loadDCEL(DCEL dcel) throws Exception {
		  loadVert(dcel.getVert());
		  loadEdge(dcel.getEdge());
		  loadFace(dcel.getFace());
	}
	
    // helper method for loadDCEL(DCEL dcel) and reload(). Also can be called independently.
    // load vertices data and updates the coordinates of vertices according to the view window.
	public static void loadVert(ArrayList<Vert> inputs) throws Exception {
		  if(inputs.size() == 0)
				 throw new Exception("empty input: Vert");
		  
		  verts = inputs;
		  calculateProperties();
		  offsetVert(verts);
	}
	
    // helper method for loadDCEL(DCEL dcel) and reload(). Also can be called independently.
    // load edges data and updates the coordinates of endpoints of edge according to the view window.
	public static void loadEdge(ArrayList<Edge> inputs) throws Exception {
		  if(inputs.size() == 0)
				 throw new Exception("empty input: Edge");
		  
		  edges = inputs;
		  offsetEdge(edges);
	}
	
    // helper method for loadDCEL(DCEL dcel). Also can be called independently.
    // load faces data and initialize a color field for each face.
	public static void loadFace(ArrayList<Face> inputs) throws Exception {
		  if(inputs.size() == 0)
				 throw new Exception("empty input: Face");
		  
		  faces = inputs;
		  
		  colors = new Color[faces.size()];
		  
		  for(int i = 0; i < colors.length; i++) {
				 colors[i] = new Color(Math.random()*Math.random(), Math.random(), Math.random(), 1.0);
		  }
	}
	
    // change the width of the view window. Default is 600.
	public static void setWidth(double width) {
		  Visualization.width = width;
		  reload();
	}
	
    // change the length of the view window. Default is 600.
	public static void setLength(double length) {
		  Visualization.length = length;
		  reload();
	}
	
    // helper method to set coordinate of c to start
	private void setPos(Circle c, Vert start) {
		  c.setCenterX(start.x);
		  c.setCenterY(start.y);
		  c.setRadius(3);
	}
	
    // helper method to set coordinate of t to start
	private void setPos(Text t, Vert start) {
		  t.setX(start.x);
		  t.setY(start.y);
		  t.setText(start.name);
	}
	
	@Override 
  	@SuppressWarnings({ "rawtypes", "unchecked" })
   	public void start(Stage stage) { 
		  Group root = new Group(); 
        ObservableList list = root.getChildren(); 
        
		  // rendering edges
		  for (int i = 0; i < edges.size(); i++) {
				 Edge cur = edges.get(i);
				 Vert start = cur.getStart();
				 Vert end = cur.getEnd();
				 
				 Line line = new Line();
				 line.setStartX(start.x);
				 line.setStartY(start.y);
				 line.setEndX(end.x);
				 line.setEndY(end.y );
				 list.add(line);

		  }
		  
        // rendering vertices
		  for (int i = 0; i < verts.size(); i++) {
				 Circle c = new Circle();
				 Color color = new Color(Math.random(), Math.random(), Math.random(), 1.0);
				 c.setFill(color);
				 setPos(c, verts.get(i));
				 list.add(c);
				 
				 Text t = new Text(); 
				 setPos(t, verts.get(i));
				 list.add(t);
		  }
		  
        Scene scene = new Scene(root, width, length); 
        stage.setTitle("DCEL visualization");   
        stage.setScene(scene); 
        stage.show(); 
     }      
   
   public static void main(String args[]) throws Exception{   
   	  ArrayList<Vert> verts = new ArrayList<Vert>();
   	  ArrayList<Edge> edges = new ArrayList<Edge>();
   	  ArrayList<Face> faces = new ArrayList<Face>();
   	  
   	  Vert v0 = new Vert("v0", 3, 1.5);
   	  Vert v1 = new Vert("v1", -10, -10);
   	  Vert v2 = new Vert("v2", 3, 0);
   	  
   	  verts.add(v0);
   	  verts.add(v1);
   	  verts.add(v2);
   	  
   	  Edge e01 = new Edge("e0,1");
   	  e01.setStart(v0);
   	  e01.setEnd(v1);
   	  
   	  Edge e10 = new Edge("e1,0");
   	  e10.setStart(v1);
   	  e10.setEnd(v0);
   	  
   	  e01.setTwin(e10);
   	  e10.setTwin(e01);
   	  e01.setFace(1);
   	  e10.setFace(0);
   	  
   	  Edge e12 = new Edge("e1,2");
   	  e12.setStart(v1);
   	  e12.setEnd(v2);
   	  
   	  Edge e21 = new Edge("e2,1");
   	  e21.setStart(v2);
   	  e21.setEnd(v1);
   	  
   	  e21.setTwin(e12);
   	  e12.setTwin(e21);
   	  e12.setFace(1);
   	  e21.setFace(0);
   	  
   	  Edge e02 = new Edge("e0,2");
   	  e02.setStart(v0);
   	  e02.setEnd(v2);
   	  
   	  Edge e20 = new Edge("e2,0");
   	  e20.setStart(v2);
   	  e20.setEnd(v0);
   	  
   	  e02.setTwin(e20);
   	  e20.setTwin(e02);
   	  e20.setFace(1);
   	  e02.setFace(0);
   	  
   	  e01.setNext(e12);
   	  e12.setNext(e20);
   	  e20.setNext(e01);
   	  
   	  e10.setNext(e02);
   	  e02.setNext(e21);
   	  e21.setNext(e10);
   	  
   	  edges.add(e01);
   	  edges.add(e10);
   	  edges.add(e12);
   	  edges.add(e21);
   	  edges.add(e20);
   	  edges.add(e02);
   	  
   	  ArrayList<Edge> f0Inner = new ArrayList<Edge>();
   	  f0Inner.add(e01);
   	  
   	  Face f0 = new Face(0, null, f0Inner);
   	  Face f1 = new Face(1, e10, null);
   	  
   	  faces.add(f0);
   	  faces.add(f1);
   	  
   	  DCEL dcel = new DCEL(verts, edges, faces);
   	  
   	  Visualization.loadDCEL(dcel);
   	  Visualization.launch();  
   }         
} 
