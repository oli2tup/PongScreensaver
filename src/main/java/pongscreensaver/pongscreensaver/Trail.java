package pongscreensaver.pongscreensaver;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

import java.util.LinkedList;

public class Trail extends Entity {
    LinkedList<TrailElement> trail = new LinkedList<>();
    double ballX, ballY;
    private double lineWidth;
    private Color color;

    public Trail(double lineWidth) {
        this.lineWidth = lineWidth;
        this.color = Color.WHITE;
    }

    public void addPoint(double x, double y, Color colour) {
        trail.add(new TrailElement(x, y, colour));
    }

    @Override
    public void draw(GraphicsContext g2d) {
        TrailElement previous = trail.get(0);
        if (previous != null) {
            for (TrailElement current : trail) {


                g2d.setLineWidth(this.lineWidth);
                g2d.setLineCap(StrokeLineCap.ROUND);

                g2d.strokeLine(previous.x, previous.y, current.x, current.y);
                g2d.setStroke(current.getColour());
                previous = current;
            }
        }
        if(previous != null) g2d.strokeLine(previous.x, previous.y, ballX, ballY);
    }

    public void update(Ball ball) {
        ballX = ball.getCentreX();
        ballY = ball.getCentreY();
        this.color = ball.color;
    }

    public LinkedList<TrailElement> getTrail() {
        return trail;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public void trailReset(Ball ball){
        this.trail.clear();
        this.trail.add(new TrailElement(ball.getCentreX(), ball.getCentreY(), ball.color));
    }

    public int getNumberOfElements() {
        return trail.size();
    }
}
