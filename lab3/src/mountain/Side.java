package mountain;

public class Side {
	private Point p1;
	private Point p2;
	private Point midpoint;

	public Side(Point p1,Point p2,double deviation) {
		this.p1=p1;
		this.p2=p2;
		midpoint = new Point((p1.getX()+p2.getX())/2.0,(p1.getY()+p2.getY())/2.0 + deviation);
	}
	public Point getOneEnd() {
		return p1;
	}
	public Point getOtherEnd() {
		return p2;
	}
	public Point getMid() {
		return midpoint;
	}
	public boolean equals(Point point1, Point point2) {
		/** If the points are equal, then return true. */
		if ((point1.equals(p1) && point2.equals(p2)) || (point1.equals(p2) && point2.equals(p1))) {
			return true;
		} else {
			return false;
		}
	}
}