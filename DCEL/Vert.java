
/***
 * This class is used to represent vertex in DCEL.
 */

public class Vert extends point {
    public final String name;
    private Edge incidentEdge; // and edge that has "this" as start/origin

    Vert(String name, double x, double y) {
        super(x, y);
        this.name = name;
        incidentEdge = null;
    }

    public Edge getIncident() {
        return incidentEdge;
    }

    public void setIncident(Edge e) {
        incidentEdge = e;
    }

    public String toString() {
        return name + " (" + x + ", " + y + ") ";
    }
}
