package controller;

public class Feld {

	private String name;
	private int punkt1X;
	private int punkt1Y;
	private int punkt2X;
	private int punkt2Y;
	private int punkt3X;
	private int punkt3Y;
	private int punkt4X;
	private int punkt4Y;

	public Feld(String name, int punkt1x, int punkt1y, int punkt2x, int punkt2y, int punkt3x, int punkt3y, int punkt4x,
			int punkt4y) {
		super();
		this.name = name;
		punkt1X = punkt1x;
		punkt1Y = punkt1y;
		punkt2X = punkt2x;
		punkt2Y = punkt2y;
		punkt3X = punkt3x;
		punkt3Y = punkt3y;
		punkt4X = punkt4x;
		punkt4Y = punkt4y;
	}

	public int[] getPoint1() {
		return new int[] { punkt1X, punkt1Y };
	}

	public int[] getPoint2() {
		return new int[] { punkt2X, punkt2Y };
	}

	public int[] getPoint3() {
		return new int[] { punkt3X, punkt3Y };
	}

	public int[] getPoint4() {
		return new int[] { punkt4X, punkt4Y };
	}

	public String getName()

	{
		return this.name;
	}
}
