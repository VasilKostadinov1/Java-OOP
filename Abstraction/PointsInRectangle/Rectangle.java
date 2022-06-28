package Abstraction_Lab.PointsInRectangle;

public class Rectangle {

    private Point pointA;
    private Point pointB;

    public Rectangle(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public boolean isInside(Point p) {
        return p.isGreaterThanOrEqual(pointA) &&
                p.isLessThanOrEqual(pointB);
    }
}
