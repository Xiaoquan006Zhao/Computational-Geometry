
import java.util.ArrayList;
import javafx.application.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

/***
 * Using javafx to display the convex hull after calculation using either implementation.
 * One example is given below in main method.
 * loadData and loadPoints must be used. Data is the ch, and points are points given to calculate the ch.
 */

@SuppressWarnings("restriction")
public class Visualization extends Application { 
	private static ArrayList<point> data = null; //points of CH that need to be displayed.
	private static ArrayList<point> points = null;
	
	// scene width and length
	private static double width = 600; //default = 600
	private static double length = 600; //default = 600
	
	private static ArrayList<point> offset(ArrayList<point> inputs) {
		  double xmax = inputs.get(0).x;
		  double ymax = inputs.get(0).y;
		  double xmin = inputs.get(0).x;
		  double ymin = inputs.get(0).y;
		  
		  for(int i = 1; i < inputs.size(); i++) {
				 point p = inputs.get(i);
				 
				 if(p.x > xmax) 
						xmax = p.x;
				 else if(p.x < xmin) 
						xmin = p.x;
				 
				 if(p.y > ymax) 
						ymax = p.y;
				 else if(p.y < ymin) 
						ymin = p.y;
		  }
		  
		  point center = new point((xmax+xmin)/2, (ymax+ymin)/2);
		  System.out.println(center.x + " ," + center.y);
		  ArrayList<point> adjusted = new ArrayList<point>();
		  
		  for(int i = 0; i < inputs.size(); i++) {
				 double newX = 0;
				 double newY = 0;
				 double oldX = inputs.get(i).x;
				 double oldY = inputs.get(i).y;
				 
				 newX = (width/2 * 9/10) * Math.abs((oldX - center.x) / (xmax - center.x));
				 newY = (length/2 * 9/10) * Math.abs((oldY - center.y) / (ymax - center.y));
				 
				 newX = Double.isNaN(newX)? 0 : newX;
				 newY = Double.isNaN(newY)? 0 : newY;
				 
				 newX = (oldX < center.x)? -newX : newX;
				 newY = (oldY < center.y)? -newY : newY;
				 
				 newX += width/2;
				 newY += length/2;
				 
				 newY = length - newY;
				 
				 System.out.println(newX);
				 System.out.println(newY);
				 adjusted.add(new point(newX, newY));
		  }
		  
		  return adjusted;
	}
	
	
	public static void loadData(ArrayList<point> inputs) {
		  data = offset(inputs);
	}
	
	public static void loadPoints(ArrayList<point> inputs) {
		  points = offset(inputs);
	}
	
	public static void setWidth(double width) {
		  Visualization.width = width;
		  data = offset(data);
	}
	
	public static void setLength(double length) {
		  Visualization.length = length;
		  data = offset(data);
	}
	
	private void setPos(Circle c, point start) {
		  c.setCenterX(start.x);
		  c.setCenterY(start.y);
		  c.setRadius(3);
	}
	  
	@Override 
  	@SuppressWarnings({ "rawtypes", "unchecked" })
   	public void start(Stage stage) { 
		  Group root = new Group(); 
        	  ObservableList list = root.getChildren(); 
        
		  point start = data.get(0);
		  
		  for (int i = 0; i < points.size(); i++) {
				 Circle c = new Circle();
				 Color color = new Color(Math.random(), Math.random(), Math.random(), 1.0);
				 c.setFill(color);
				 setPos(c, points.get(i));
				 list.add(c);
		  }
			 
		  for (int i = 1; i < data.size(); i++) {
				 point end = data.get(i);
				 Line line = new Line();
				 line.setStartX(start.x);
				 line.setStartY(start.y);
				 line.setEndX(end.x);
				 line.setEndY(end.y);
				 list.add(line);
				 start = end;
		  }
		  
		  point end = data.get(0);
		  Line line = new Line();
		  line.setStartX(start.x);
		  line.setStartY(start.y);
		  line.setEndX(end.x);
		  line.setEndY(end.y);
		  list.add(line);
		  
        	  Scene scene = new Scene(root, width, length); 
        	  stage.setTitle("Convex hull visualization");   
        	  stage.setScene(scene); 
       	 	  stage.show(); 
     }      
   
   public static void main(String args[]) throws Exception{   
   	 	  ArrayList<point> input = new ArrayList<point>();
 		 
   	 	  input.add(new point(0, 0));
   		  input.add(new point(2, 2));
   	 	  input.add(new point(0, 1));
   	 	  input.add(new point(-2, 2));
   	 	  input.add(new point(0, -1));
   	 	  input.add(new point(10, 8));
   	 	  input.add(new point(2, -2));
   	 
 		  GrahamScan ex = new GrahamScan(input);
   	  
   	 	  Visualization.loadData(ex.getCH());
   	 	  Visualization.loadPoints(ex.getPoints());
   	 	  Visualization.launch();  
   }         
} 
