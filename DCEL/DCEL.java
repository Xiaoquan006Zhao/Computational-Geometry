
import java.util.ArrayList;

public class DCEL {

	 private ArrayList<Vert> vertices = new ArrayList<Vert>();
	 private ArrayList<Edge> edges = new ArrayList<Edge>();
	 private ArrayList<Face> faces = new ArrayList<Face>();

	 public DCEL(ArrayList<Vert> vertices, ArrayList<Edge> edges, ArrayList<Face> faces) {
		  this.vertices = vertices;
		  this.edges = edges;
		  this.faces = faces;
	 }

	 public DCEL() {
	 }

	 public void setVert(ArrayList<Vert> vertices) {
		  this.vertices = vertices;
	 }

	 public ArrayList<Vert> getVert() {
		  return vertices;
	 }

	 public void setEdge(ArrayList<Edge> edges) {
		  this.edges = edges;
	 }

	 public ArrayList<Edge> getEdge() {
		  return edges;
	 }

	 public void setFace(ArrayList<Face> faces) {
		  this.faces = faces;
	 }

	 public ArrayList<Face> getFace() {
		  return faces;
	 }
}
