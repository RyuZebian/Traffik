package trafficSim;


// H�ller i en bil och k�nner till sina "grannar". 
public class CarPosition{
	
	private Car currentCar = null; // null om ingen bil finns p� positionen
	
	public Car getCurrentCar() {
		return currentCar;
	}
	// id-nummer
	private int idNumber;
	
	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	private Lane owner;
	
	private CarPosition forward;
	
	public Lane getOwner() {
		return owner;
	}

	public void setOwner(Lane owner) {
		this.owner = owner;
	}

	public CarPosition getForward() {
		return forward;
	}

	public void setForward(CarPosition forward) {
		this.forward = forward;
	}

	public void setCurrentCar(Car currentCar) {
		this.currentCar = currentCar;
	}

	private CarPosition turn;
	
	public CarPosition()
	{

	}
	
	public boolean isEnd(CarPosition target)
	{
		return owner.matchEnd(target);
	}
	
	public void moveForward()
	{
		if (this.getCurrentCar() != null && this.getForward().getCurrentCar() == null){
		currentCar.setCurrentPosition(getForward());
		getForward().setCurrentCar(this.getCurrentCar());
		this.setCurrentCar(null);
		}
		
		// Flytta bilen fram till forward: uppdaterar car's currentPos och carPos currentCar
	}
	
	public boolean turn()
	{
		return false;
		// Flytta bilen till turn
	}

	public void setTurn(CarPosition turn) {
		this.turn = turn;
	}

	public CarPosition getTurn() {
		return turn;
	}
	public String toString() {
		return "" + this.idNumber;
	}
	
	public boolean equals(Object target){
    	if (target == "D1" || (int) target == -1 || this == target)
    		return true;
    	else return false;
    }
    
    public boolean equals(CarPosition positionToTest)
    {
    	if (this.getCurrentCar() == positionToTest.getCurrentCar() && 
    			this.getForward() == positionToTest.getForward() &&
    			this.getIdNumber() == positionToTest.getIdNumber() &&
    			this.getOwner() == positionToTest.getOwner()) {    			
    		return true; 
    		}
    	else
    		return false;
    }
	
	
}
