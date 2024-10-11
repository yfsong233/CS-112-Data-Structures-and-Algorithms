/*
 * Point.java
 *
 * A blueprint class representing a point on the Cartesian plane.
 */

public class Point {
    // the fields inside every Point object
    private int x;
    private int y;

    /* 
     * Note that each coordinate can take on any integer value, 
     * which means that the mutators don’t need to perform any error-checking, 
     * and that we also don’t need to add any error-checking to the constructor. 
     * However, keep in mind that ordinarily you do need error-checking 
     * in your mutators and constructors.
     */


    /*
     * constructor that takes values for both coordinates
     */
    public Point(int initX, int initY) {
        this.x = initX;
        this.y = initY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /*
     * equals - returns whether two Point objects are equal
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y); 
    }

    /*
     * toString - returns a String representation of a Point object 
     * 
     * COMMENTED OUT FOR NOW
     */
    public String toString() {
       return "(" + this.x + ", " + this.y + ")"; 
    }

    /*
     * distanceFromOrigin - an accessor method that returns the
     * distance of this Point from the origin
     */
    public double distanceFromOrigin() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    public int quadrant() {
        if (this.x > 0 && this.y > 0) {
            return 1;
        } else if (this.x < 0 && this.y > 0) {
            return 2;
        } else if (this.x < 0 && this.y < 0) {
            return 3;
        } else if (this.x > 0 && this.y < 0) {
            return 4;
        } return 0;
    }

    public static Point closestToOrigin(Point p1, Point p2) {
        double p1Distance = p1.distanceFromOrigin();
        double p2Distance = p2.distanceFromOrigin();
        if (p1Distance < p2Distance) {
            return p1;
        } return p2;
    }
}
