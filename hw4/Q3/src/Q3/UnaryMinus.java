package Q3;
/**
 * implements UnaryMinus operation
 * given an expression , this class will return UnaryMinus of it 
 * 
 * Abstraction Function :
 * e1 is the Expression that UnaryMinus will be apllied on
 * n1 is the Number that UnaryMinus will be apllied on
 * isLeaf - boolean flag that marks if the element we working on is an expression or number
 * 
 * Representation Invarinat :
 * e1 is an Expression or null 
 * n1 is a Number or null 
 * only on of e1 and n1 is null 
 */
public class UnaryMinus extends Expression {
	
	public Expression e1 ;
	private boolean isLeaf; 
	public Number n1 ; 
	/**
	 * constructor for given expression 
	 * @param e1 - given expression 
	 */
	public UnaryMinus(Expression e1 ) {
		super(-1 * e1.eval().doubleValue());
		this.e1 = e1 ;
		this.n1 = null ; 
		isLeaf = false ; 
		checkRep();
	}
	/**
	 * constructor for given number 
	 * @param n1 - given number 
	 */
	public UnaryMinus(Number n1) {
		super(-1 * n1.doubleValue());
		this.e1 = null ;
		this.n1 = n1 ; 
		isLeaf = true ; 
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
		assert (e1 != null && n1 != null) : "Error ";
		assert (e1 == null && n1 == null) : "Error ";
	}
	/**
	 * @return String of " -Expression" or " -Number" 
	 */
	@Override
	public String toString() {
		checkRep();
		if (!isLeaf) {
			String retString = "( -" + e1.toString() + ") ";
			checkRep();
			return retString ; 
		}
		else {
			String retString = "( -" + n1.toString() + ") ";
			checkRep();
			return retString ; 
		}
	}
}



