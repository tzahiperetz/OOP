package Q3;

public class main {

	public static void main(String[] args) {
		
		Expression e = new Multipication(
						new Addition( Double.valueOf(2.5),Double.valueOf(3.5)),
						new UnaryMinus( Integer.valueOf(5))
						);				
		System.out.println(e.eval());
		System.out.println(e.toString());
		
		
		Expression e2 = new Division (
						new Minus( Double.valueOf(9),Double.valueOf(4.5)),
						1.5
						);			
		System.out.println(e2.eval());
		System.out.println(e2.toString());
		
		Expression e3 = new Division (
				10 , 
				new UnaryMinus( Integer.valueOf(5))
				);			
		System.out.println(e3.eval());
		System.out.println(e3.toString());

		Expression e4 = new Addition (
				3.5,
				1.5
				);			
		System.out.println(e4.eval());
		System.out.println(e4.toString());
	
		Expression e5 = new Addition (
				new Addition (new Multipication(1 ,1) ,new Multipication(1,1) ),
				new Addition (new Division(1 ,1) ,new Division(1,1))
				);			
		System.out.println(e5.eval());
		System.out.println(e5.toString());
}
	
}

