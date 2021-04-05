package Q3;
/**
 * This object represent an expression that contains a value
 * and can return it as string or as number 
 * morevoer ,expression could be result of an arithmetic operation
 * abstract function : 
 * Expression holds value which is the numeric representation .
 * 
 * Represntation Invariant :
 * Value can be all kind of numbers -(Integer,Double,Long ,...)	
 */
abstract public class Expression {
	public Number value ;
	
	/**
	 * Construct a new expression object
	 * @param e - the numeric value 
	 */
	public Expression(Number e) {
		this.value=e ;
		checkRep ();
	}
	/**
	 * check Representation Invaraint
	 */
	public void checkRep () { 
		assert !(value instanceof Number) : "Wrong type of value ";
	}
	/**
	 *
	 * @return the value of the expression
	 */
	public Number eval() {
		checkRep (); 
		return this.value ;}
	/**
	 * abstract method -> suppose to return string of the arithemtic expression
	 */
	abstract public String toString();
	}
