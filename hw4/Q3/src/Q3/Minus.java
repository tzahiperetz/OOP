package Q3;
/**
 * implements Minus operation
 * given 2 expressions ,a and b , this class will return result of a-b 
 * 
 * Abstraction Function :
 * e1,e2 are the Expressions that Minus will be apllied on
 * n1,n2 are the Numbers that Minus will be apllied on
 * type - hold the combination input case : 
 * 	      0 -> SRC+ DST expressions 
 *   	  1 -> SRC expression ,DST number ,
 *		  2 -> SRC number DST expression 
 *	 	  3 -> SRC +DST numbers
 * 
 * Representation Invaraint :
 * e1,e2 are Expression or null 
 * n1,n2 are Number or null 
 * only one pair of elements (n1,n2),(e1,e2),(n1,e2),(e1,n2) is valid.
 * type is an integer between 0 to 3 
 */
public class Minus extends Expression {
	
	public Expression e1 ;
	public Expression e2 ;
	private int type; 
	public Number n1 ; 
	public Number n2 ; 
	/**
	 * constructor
	 * @param e1 - first given element 
	 * @param e2 - second given element
	 */
	public Minus(Expression e1 ,Expression e2) {		
		super(e1.eval().doubleValue() - e2.eval().doubleValue());
		this.e1 = e1 ;
		this.e2 = e2 ; 
		this.n1 = null ;
		this.n2 = null ; 
		type = 0 ;
		checkRep();

	}
	/**
	 * constructor
	 * @param e1 - first given element 
	 * @param n2 - second given number
	 */
	public Minus(Expression e1 ,Number n2) {	
		super(e1.eval().doubleValue() - n2.doubleValue());
		this.e1 = e1 ;
		this.e2 = null ; 
		this.n1 = null ;
		this.n2 = n2 ; 
		type = 1 ;
		checkRep();

	}
	/**
	 * constructor
	 * @param n1 - first given number 
	 * @param e2 - second given expression
	 */
	public Minus(Number n1 ,Expression e2) {		
		super(n1.doubleValue() - e2.eval().doubleValue());
		this.e1 = null ;
		this.e2 = e2 ; 
		this.n1 = n1 ;
		this.n2 = null ; 
		type = 2 ;
		checkRep();

	}
	/**
	 * constructor
	 * @param n1 - first given number 
	 * @param n2 - second given number
	 */
	public Minus(Number n1, Number n2) {
		super(n1.doubleValue() - n2.doubleValue());
		this.e1 = null ;
		this.e2 = null ; 
		this.n1 = n1 ;
		this.n2 = n2 ; 
		type = 3 ;
		checkRep();

	}
	/**
	 * assert an error if on of our representation doesnt exist. 
	 */
	public void checkRep() {
		// recieved number 
		if (n1 != null )
			assert !(n1 instanceof Number) : "Wrong type of n1 ";
		if (e1 != null )
			assert !(e1 instanceof Expression) : "Wrong type of e1 ";
		if (n2 != null ) 
			assert !(n2 instanceof Number) : "Wrong type of n2 ";
		if (e2 != null ) 
			assert !(e2 instanceof Expression) : "Wrong type of e2 ";

		assert (e1 == null && n1 == null) : "Error ";
		assert (e2 == null && n2 == null) : "Error ";
		assert (type >= 0 && type < 4 )   : "Error";
	}
	

	/**
	 * @return String of "first argument - second argument " 
	 */
	 @Override
	public String toString() {
		checkRep();
		String retStr = "";
		if ( type == 0 ) {
			 retStr = "(" + e1.toString() + " - " + e2.toString() + " )"; 
		}
		else if(type == 1)  {
			 retStr = "(" + e1.toString() + " - " + n2.toString() + " )"; 
		}
		else if(type == 2)  {
			 retStr = "(" + n1.toString() + " - " + e2.toString() + " )"; 
		}
		else if(type == 3)  {
			 retStr = "(" + n1.toString() + " - " + n2.toString() + " )"; 
		}
		checkRep();
		return retStr ; 
	}

}
