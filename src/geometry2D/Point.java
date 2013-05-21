
package geometry2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: ivan.bendyna Date: 03.04.13
 */
public class Point extends GeometricObject implements Comparable<Point> {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        x = p.x;
        y = p.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public void shift(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void rotate(double angleDegreeCcw) {
        double length = Math.sqrt(y * y + x * x);
        double currentAngle = Math.atan2(y, x) * 180.0 / Math.PI;
        currentAngle += angleDegreeCcw;
        currentAngle = currentAngle * Math.PI / 180;
        x = length * Math.cos(currentAngle);
        y = length * Math.sin(currentAngle);
    }

    @Override
    public void rotate(Point pivot, double angleDegreeCcw) {
        shift(-pivot.getX(), -pivot.getY());
        rotate(angleDegreeCcw);
        shift(pivot.getX(), pivot.getY());
    }

    @Override
    public List<GeometricObject> intersect(GeometricObject otherObject) {
        return new ArrayList<GeometricObject>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point otherPoint = (Point) obj;
            return (Math.abs(x - otherPoint.x) < EPS)
                            && (Math.abs(y - otherPoint.y) < EPS);
        }
        else {
            return false;
        }
    }

    public double squareDistance(Point otherPoint) {
        return (x - otherPoint.x) * (x - otherPoint.x) + (y - otherPoint.y)
                        * (y - otherPoint.y);
    }

    public double distance(Point otherPoint) {
        return Math.sqrt(squareDistance(otherPoint));
    }

    @Override
    public int compareTo(Point otherPoint) {
        if (x < otherPoint.x - EPS
                        || (Math.abs(x - otherPoint.x) < EPS && y < otherPoint.y
                                        - EPS)) {
            return -1;
        }
        else if (this.equals(otherPoint)) {
            return 0;
        }
        else {
            return 1;
        }
    }

}
