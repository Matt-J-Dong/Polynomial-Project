

import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @Name Your Name
 * @Date Due Date
 * @Period Class Period
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextDouble(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		Node output = null;
		Node polyOne = poly1;
		Node polyTwo = poly2;
		
		//System.out.print("q"); debug line
		
		if (poly1 == null) {
			output = new Node(poly2.term.coeff, poly2.term.degree, poly2.next);
			return output;
		} else if (poly2 == null) {
			output = new Node(poly1.term.coeff, poly1.term.degree, poly1.next);
			return output;
		}
		
		//System.out.print("w"); debug line
		
		if (polyOne.term.degree > polyTwo.term.degree)
			polyOne = new Node(polyTwo.term.coeff, polyTwo.term.degree, polyOne);
		//System.out.print("z"); debug line
		for (Node i = polyOne; i != null; i = i.next) {
			//System.out.print("a"); debug line
			for (Node k = polyTwo; k != null; k = k.next) {
				if (i.term.degree == k.term.degree)
					i.term.coeff += k.term.coeff;
				//System.out.print("b"); debug line
			}
		}
		
		for (Node i = polyOne; i != null; i = i.next) {
			//System.out.print("c"); debug line
			for (Node k = polyTwo; k != null; k = k.next) {
				//System.out.print("d"); debug line
				if (i.term.degree < k.term.degree) {
					if (i.next == null) {
						i.next = new Node(k.term.coeff, k.term.degree, null);
					}
					else if (i.next.term.degree > k.term.degree) {
						i.next = new Node(k.term.coeff, k.term.degree, i.next); }
				}
			}
		}

		return polyOne;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** Your code goes here **/
		Node output = null;
		Node polyOne = poly1;
		Node polyTwo = poly2;
		Node temp;
		
		if (poly1 == null || poly2 == null) return output;	
		
		for (Node i = polyOne; i != null; i = i.next) {
			for (Node k = polyTwo; k != null; k = k.next) {
				temp = new Node (i.term.coeff * k.term.coeff, i.term.degree + k.term.degree, null);
				//System.out.print("A"); debug line
				output = add(output, temp);
			}
			//System.out.print("B"); debug line
		}
				
		return output;
		
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static double evaluate(Node poly, double x) {
		/** Your code goes here **/
		double output = 0;
		
		if (poly == null) return output;
		
		for (Node i = poly; i != null; i = i.next) {
			output = output + i.term.coeff * Math.pow(x, i.term.degree);
		}

		return output; //Quiets the compiler
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
