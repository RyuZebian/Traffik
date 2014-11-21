package trafficSim;

public class Lane {

    public static class OverflowException extends RuntimeException {
        {
    	System.err.println("Lane is full!");
        }	
    	// Undantag som kastas n�r det inte gick att l�gga 
        // in en ny bil p� v�gen
    }
    protected CarPosition[] theLane;

    public Lane(int n) {
    	theLane = new CarPosition[(n+1)];
    	for (int i = 0; i <= n; i++) {
    		theLane[i] = new CarPosition();
    		theLane[i].setIdNumber(i);
    	}
    	for (int i = getLength()-1; i > 0; i--) {
    		theLane[i].setForward(theLane[i-1]); 
    	}
    // Konstruerar ett Lane-objekt med plats f�r n fordon
    // Samt l�nker ihop varje CarPosition med forward f�r den framf�r
    }
    
    public boolean matchEnd(CarPosition target) {
    	if(theLane[0] == target) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public int getLength(){
    	return theLane.length;
    }
    
    public void setParallel(Lane sideLane) {
    	int i = 0;
    	while(i < sideLane.getLength() && i < theLane.length)
    	{
    		theLane[i].setTurn(sideLane.theLane[i]);
    		i++;
    	}
    }

    public void step() {
    	for (int i = 1; i < (theLane.length); i++) {
    		theLane[i].moveForward();
    	}   	
    // Stega fram alla fordon (utom det p� plats 0) ett steg 
    // (om det g�r). (Fordonet p� plats 0 tas bort utifr�n 
	// mm h a metoden nedan.)
    }

    public Car getFirst() {
    	Car car = theLane [0].getCurrentCar();
    	theLane [0].setCurrentCar(null);
    	return car;
	// Returnera och tag bort bilen som st�r f�rst
    }

    public Car firstCar() {
    	return theLane [0].getCurrentCar();
	// Returnera bilen som st�r f�rst utan att ta bort den
    }


    public boolean lastFree() {
    	if (theLane [(theLane.length-1)].getCurrentCar() == null) {
    		return true;
    	}
    	else { 
    		return false;
    	}
    		// Returnera true om sista platsen ledig, annars false
    }

    public void putLast(Car c) throws OverflowException {
    int laneLength = getLength();
    if (lastFree()) {
    	c.setCurrentPosition(theLane [(laneLength-1)]);  
    	theLane [(laneLength-1)].setCurrentCar(c);
    }
    else { 
    	throw new OverflowException();
    	}
    // St�ll en bil p� sista platsen p� v�gen
   	// (om det g�r).
    }
  		
			
    public String printStatText() {
    	String carPosInLane = "";
    	for (int i = 0; i < theLane.length; i++) {
    		if (theLane[i].getCurrentCar() == null) {
    			carPosInLane += "'\n'CarPosition " + theLane[i].toString() +" is empty" + "\n' ----------------------------";
    		}
    		else { 
    			carPosInLane += "'\n'CarPosition " + theLane[i].toString() + " has car with: " + theLane[i].getCurrentCar().toString() + "\n' ----------------------------";;
    		}
    	}
    	return carPosInLane;
    	}
   
    public String toString() {
    	String carPosInLane = "";
    	for (int i = 0; i < theLane.length -1; i++) {
    		if (theLane[i].getCurrentCar() == null) {
    			carPosInLane += "-";
    		}
    		else {
    			carPosInLane += "C";
    		}
    	}
    	return carPosInLane;
    }
    		
}
