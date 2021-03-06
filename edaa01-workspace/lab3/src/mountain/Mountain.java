package mountain;

import fractal.*;
import java.util.*;

public class Mountain extends Fractal {

	private Point a, b, c;
	private double dev;
	private List<Side> sides = new LinkedList<Side>();

	public Mountain(int length, double dev) {
		super();
		this.dev = dev;
		a = new Point(100, length + 100);
		b = new Point(100 + length, length + 100);
		c = new Point((length + 100) / 2.0, 100);
	}

	public String getTitle() {
		return "Mountain";
	}

	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, order, a, b, c, dev);
	}

	public void fractalTriangle(TurtleGraphics turtle, int order, Point a,
			Point b, Point c, double dev) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			dev = dev / 2;
			/*
			 * Point ny1 = new Point((b.getX() + a.getX())/2.0, ((b.getY() +
			 * a.getY())/2.0) - RandomUtilities.randFunc(dev)); Point ny2 = new
			 * Point((c.getX() + a.getX())/2.0, ((c.getY() + a.getY())/2.0) -
			 * RandomUtilities.randFunc(dev)); Point ny3 = new Point((c.getX() +
			 * b.getX())/2.0, ((c.getY() + b.getY())/2.0) -
			 * RandomUtilities.randFunc(dev));
			 */
			Point ny1 = checkMiddle(new Side(a, b, dev));
			Point ny2 = checkMiddle(new Side(a, c, dev));
			Point ny3 = checkMiddle(new Side(b, c, dev));

			fractalTriangle(turtle, order - 1, a, ny1, ny2, dev);
			fractalTriangle(turtle, order - 1, b, ny3, ny1, dev);
			fractalTriangle(turtle, order - 1, c, ny2, ny3, dev);
			fractalTriangle(turtle, order - 1, ny1, ny2, ny3, dev);
		}

	}

	private Point checkMiddle(Side s) {
		Point middle = s.getMiddle();
		for (int i = 0; i < sides.size(); i++) {
			Side temp = sides.get(i);
			if (temp.equals(s.getA(), s.getB())) {
				return sides.remove(i).getMiddle();
			}
		}
		sides.add(s);
		return middle;
	}

}
