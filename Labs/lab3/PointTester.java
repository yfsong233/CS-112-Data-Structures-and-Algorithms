public class PointTester {
    
    public static void main(String[] args) {
        Point p1 = new Point(2, 5);

        System.out.println("x = " + p1.getX());
        System.out.println("y = " + p1.getY());  // test for encapsulation

        System.out.println(p1);  // Point@4e25154f if without a toString method

        Point p2 = new Point(3, 4);
        System.out.println(p2.quadrant());  // 1
        Point p3 = new Point(-3, -4);
        System.out.println(p3.quadrant());  // 3
        Point p5 = new Point(0, -4);
        System.out.println(p5.quadrant());  // 0

        Point p6 = new Point(3, 4);
        Point p7 = new Point(2, -1);
        System.out.println(Point.closestToOrigin(p6, p7));  // (2, -1)
    }
}
