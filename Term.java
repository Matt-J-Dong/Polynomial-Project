

/**
 * This class implements a term of a polynomial.
 * 
 * @author runb-cs112
 *
 */
public class Term {
	
	/**
	 * Coefficient of term.
	 */
	public double coeff;
	
	/**
	 * Degree of term.
	 */
	public int degree;
	
	/**
	 * Initializes an instance with given coefficient and degree.
	 * 
	 * @param coeff Coefficient
	 * @param degree Degree
	 */
	public Term(double coeff, int degree) {
		this.coeff = coeff;
		this.degree = degree;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(other != null && other instanceof Term)
		{
			return Math.abs(coeff - ((Term)other).coeff) < 0.00000000001 && 
					degree == ((Term)other).degree;
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (degree == 0) {
			return "" + coeff;
		} else if (degree == 1) {
			return coeff + "x";
		} else {
			return coeff + "x^" + degree;
		}
	}
}
