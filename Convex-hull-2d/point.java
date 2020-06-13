
// this class is used to represent points in 2d space and referenced in the convex-hull computing classes.

public class point {
    // once a point is initialized there is no need to modify it further more in
    // future programs.
    // Thus make the varables "final".

    public final double x;
    public final double y;

    public point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
