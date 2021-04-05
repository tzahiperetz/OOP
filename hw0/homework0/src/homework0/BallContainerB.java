package homework0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A container that can be used to contain Balls. A given Ball may only
 * appear in a BallContainer once. Each container has a size, and can only contain balls up to the size of the container.
 */
public class BallContainerB {

	private double containerSize_; // volume size 
	List<Ball> ballsList ;
	
    /**
     * @effects Creates a new BallContainer with the size of containerSize.
     */
    public BallContainerB(double containerSize) {
    	if (containerSize < 0) {
    		System.out.println("Invalid containerSize - must be bigger than 0 ");
    		return;
		}
    	this.containerSize_ = containerSize;
    	this.ballsList =  new ArrayList<>();
    }

    /**
     * @modifies this
     * @effects Adds ball to the container.
     * @return true if ball was successfully added to the container,
     * 		   i.e. ball is not already in the container and if adding ball does not cause 
	 *		   the total volume of the balls in the container to exceed the size of	the container;
	 * 		   false otherwise.
     */
    public boolean add(Ball ball) {
    	if (ball == null ) {
    		System.out.println(("Invalid ball - the ball is null ."));
    		return false; 
    	}
    	if  (this.ballsList.contains(ball)) {
    		System.out.println(("Add failed - ball already in the list ."));
    		return false ; 
    	}
    	if  ((this.getContainerSize() - this.getVolume()) < ball.getVolume()) {
    		System.out.println(("Add failed - not enough place ."));
    		return false ; 
    	}
    	
    	
    	if ( this.ballsList.add(ball) ) {
    		return true; 
    	}
    	return false; //if add fails for some reason .
    }


    /**
     * @modifies this
     * @effects Removes ball from the container.
     * @return true if ball was successfully removed from the container,
     * 		   i.e. ball is actually in the container; false otherwise.
     */
    public boolean remove(Ball ball) {
    	if (ball == null ) {
    		System.out.println("Invalid ball - the ball is null .");
    		return false; 
    	}

    	if (this.ballsList.remove(ball)) {
    		return true; 
    	}
    	return false ; // remove went wrong
    }


    /**
     * @return the volume of the contents of the container, i.e. the
     * 		   total volume of all Balls in the container.
     */
    public double getVolume() {
    	double volume =0;
    	for (Ball x : this.ballsList) {
    		volume += x.getVolume();
    	}
    	return volume;
    }
	
	/**
     * @return the size of the container.
     */
    public double getContainerSize() {
    	return (this.containerSize_);
    }


    /**
     * @return the number of Balls in the container.
     */
    public int size() {
    	return (this.ballsList.size());
    }


    /**
     * @modifies this
     * @effects Empties the container, i.e., removes all its contents.
     */
    public void clear() {
    	this.ballsList.clear();
    	return;
    }


    /**
     * @return true if this container contains ball; false, otherwise.
     */
    public boolean contains(Ball ball) {
    	if (ball == null ) {
    		System.out.println("Invalid ball - the ball is null .");
    		return false ;
    	}
    	return (this.ballsList.contains(ball));
    }
}
