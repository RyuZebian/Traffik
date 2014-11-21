package trafficSim;

public class Car {
	
    private int bornTime;
    
    public CarPosition getDestination() {
		return destination;
	}
	public void setDestination(CarPosition destination) {
		this.destination = destination;
	}
	public CarPosition getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(CarPosition currentPosition) {
		this.currentPosition = currentPosition;
	}
	private CarPosition destination; 

    private CarPosition currentPosition;
    
    
    public void step() {
    	//görs i CarPositions moveForward	
    	// Uppdatera bilen ett tidssteg
    }
    public Car(int bornTime, CarPosition destination, CarPosition currentPosition) {
		this.setBornTime(bornTime);
		this.setDestination(destination);
		this.setCurrentPosition(currentPosition);	
    }
    // konstruktor och get- oct set-metoder
    //...

    // KAN INTE KALLAS MED NULL!!! FÅNGA INNAN.
    public String toString() {
    	if (this.destination.getIdNumber() == -1) {
        	return "'\n'Car born time: " + this.getBornTime() + " '\n'Car destination: D1" + "'\n'Car position: " + this.currentPosition;
    	}
    	else {
        	return "Car born time: " + this.getBornTime() + " '\n'Car destination: D2" + "'\n'Car position: " + this.currentPosition;
    	}
    }
    
	public int getBornTime() {
		return bornTime;
	}
	public void setBornTime(int bornTime) {
		this.bornTime = bornTime;
	}
	
}
