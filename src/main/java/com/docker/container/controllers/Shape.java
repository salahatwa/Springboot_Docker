package com.docker.container.controllers;

/**
 * @author atwa Jul 29, 2018
 */
public abstract class Shape {

	abstract void Printarea();

	int a = 10, b = 2;

	/**
	 * @return the a
	 */
	public int getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(int a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public int getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(int b) {
		this.b = b;
	}

	static public class Rectangle extends Shape {

		void Printarea()

		{

			System.out.println("area of rectangle is " + (a * b));

		}

	}

	static class Triangle extends Shape

	{

		void Printarea()

		{

			System.out.println("area of triangle is " + (0.5 * a * b));

		}

	}

	static class Circle extends Shape

	{

		void Printarea()

		{

			System.out.println("area of circle is " + (3.14 * a * a));

		}

	}

	// area of circle is 314.0
	// area of rectangle is 20
	// area of triangle is 10.0
	public static void main(String[] args)

	{

		Shape b = new Circle();
		b.setA(5);
		b.setB(5);

		b.Printarea();

		b = new Rectangle();

		b.Printarea();

		b = new Triangle();

		b.Printarea();

	}

}
