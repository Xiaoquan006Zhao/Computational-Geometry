
/***
 * This class is used to represent edge in DCEL.
 */

public class Edge {
    public String name;

    private Vert start;// origin
    private Vert end;
    private Edge twin;
    private Edge prev;
    private Edge next;

    private int face;// the face that the edge bounds

    Edge(String name) {
        this.name = name;
        start = null;
        end = null;
        prev = null;
        next = null;
    }
    
    Edge(String name, Vert start, Vert end) {
        this.name = name;
        this.start = start;
        this.end  = end;
        prev = null;
        next = null;
    }

    public Vert getStart() {
        return start;
    }

    public void setStart(Vert start) {
        this.start = start;
    }

    public Vert getEnd() {
        return end;
    }

    public void setEnd(Vert end) {
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

    public void setTwin(Edge twin) {
        this.twin = twin;
    }

    public Edge getTwin() {
        return twin;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getFace() {
        return face;
    }
}
