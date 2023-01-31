package pongscreensaver.pongscreensaver;

public class Bound {
    public double upperBound, lowerBound, rightBound, leftBound;

    public Bound(double upperBound, double lowerBound, double rightBound, double leftBound) {
        this.upperBound = upperBound;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.lowerBound = lowerBound;
    }

    public Bound(double upperBound, double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.rightBound = 0;
        this.leftBound = 0;
    }
}
