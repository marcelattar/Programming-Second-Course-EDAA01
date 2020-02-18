package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;
//import mountain.RandomUtilities; //FRÅGA: Är detta nödvändigt?
import java.util.ArrayList;

public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private double dev;

	public Mountain(Point p1,Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
	}
	
	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Bergfraktaler";
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
			/**Först vill jag göra tre nya pointobjekt. */
			RandomUtilities Random = new RandomUtilities();
			double rand = Random.randFunc(dev);
			Point p12 = new Point((p1.getX()+p2.getX())/2.0,(rand+p1.getY()+p2.getY())/2.0);
			Point p23 = new Point((p2.getX()+p3.getX())/2.0,(rand+p2.getY()+p3.getY())/2.0);
			Point p31 = new Point((p1.getX()+p3.getX())/2.0,(rand+p1.getY()+p3.getY())/2.0);
			
			fractalTriangle(turtle, order-1,p1,p12,p31,dev/2);
			fractalTriangle(turtle, order-1,p2,p12,p23,dev/2);
			fractalTriangle(turtle, order-1,p3,p23,p31,dev/2);
			fractalTriangle(turtle, order-1,p12,p23,p31,dev/2); /**Denna är nödvändig om mer än 1 rekursion utförs. */
			
		}
		turtle.penUp();
	}
}
