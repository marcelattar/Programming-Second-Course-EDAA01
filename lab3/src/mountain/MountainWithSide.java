package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;
//import mountain.RandomUtilities; //FRÅGA: Är detta nödvändigt?
import java.util.ArrayList;
import java.util.Iterator;

public class MountainWithSide extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private double dev;
	private ArrayList<Side> list;

	public MountainWithSide(Point p1,Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		list = new ArrayList<Side>();
	}
	
	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Bergfraktaler med sidobjekt";
	}
	
	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, super.order, p1,p2,p3,dev); // Börjar rita.
		
	}
	
	/**
	 * Recursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3,double dev) {
		turtle.penDown();
		if (order == 0) { /**Basfall: ritar upp en enkel triangel. */
			turtle.moveTo(p1.getX(),p1.getY()); // Flyttar sköldpaddan till startpositionen.
			turtle.forwardTo(p2.getX(),p2.getY());
			turtle.forwardTo(p3.getX(),p3.getY());
			turtle.forwardTo(p1.getX(),p1.getY());
		} 
		else { /** Jag vill ersätta 1 triangel med 4a nya trianglar, o varje triangel har 3 punkter.*/
			
			double dev1 = RandomUtilities.randFunc(dev);
			double dev2 = RandomUtilities.randFunc(dev);
			double dev3 = RandomUtilities.randFunc(dev);
			
			/**Beräknar mittpunkterna */
			Point p12 = GetMidPoint(p1,p2,dev1);
			Point p23 = GetMidPoint(p2,p3,dev2);
			Point p31 = GetMidPoint(p3,p1,dev3);
			
			/**Själva rekursionen. */
			fractalTriangle(turtle, order-1,p1,p12,p31,dev/2);
			fractalTriangle(turtle, order-1,p2,p12,p23,dev/2);
			fractalTriangle(turtle, order-1,p3,p23,p31,dev/2);
			fractalTriangle(turtle, order-1,p12,p23,p31,dev/2); /**Denna är nödvändig om mer än 1 rekursion utförs. */
			
		}
		turtle.penUp();
	}
	
	private Point GetMidPoint(Point p1,Point p2, double dev) {
		if (!list.isEmpty()) {
			Iterator<Side> itr = list.iterator();
			while(itr.hasNext()) {
				Side temp = itr.next();
				if (temp.equals(p1,p2)) {
					itr.remove();
					return temp.getMid();
					}
				}
			}
		Side s = new Side(p1,p2,dev);
		list.add(s);
		return s.getMid();
		}
}
