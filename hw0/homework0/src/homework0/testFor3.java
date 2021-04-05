package homework0;

import java.io.IOException;

import homework0.Ball;
import homework0.BallContainer;
import homework0.BallContainerB;

public class testFor3 {
	public static void main(String [] args) throws IOException {
		Ball a = new Ball(1.1);
		// getvolume at the begining
		System.out.println(a.getVolume()); // return the volum of ball a - 1.1
		// set 
		a.setVolume(5.5);
		// get after set
		System.out.println(a.getVolume()); // suppose to returnn the ew valume (after set)= 5.5
		
		BallContainerB a_container = new BallContainerB(10);
		System.out.println(a_container.getContainerSize()); // suppose to return 10 
		
		Ball b = new Ball(1);
		Ball c = new Ball(2);
		Ball d = new Ball(4);
		
		a_container.add(b);
		System.out.println(a_container.getVolume()); // suppose to return 1 - only  b
		System.out.println(a_container.size()); // suppose to return 1 - only 1 elemnt in the list 
		a_container.add(a);
		a_container.add(a); // suppose to print invalid - already in the list
		a_container.add(d); // suppose to fail - not enough space 
		a_container.remove(a);
		a_container.add(d); 
		System.out.println(a_container.getVolume());// b+d=5 in the container - suppose to return 5 
		System.out.println(a_container.contains(b)); // check if b contained - print true
		System.out.println(a_container.contains(c)); // check if c contained - print false
		a_container.clear();
		System.out.println(a_container.size()); // suppose to return 0 - empty list .
		

	}
	}