package trafficSim;

public class Light {
    private int period;
    
    public int periodCounter = 0;
    // räknar hur länge vi befinner oss i pausen period
    
    public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	private int time;  // Intern klocka: 0, 1, ... period-1, 0, 1 ...
    private int green; // Signalen gr�n n�r time<green 

    public Light(int period, int green, int time) {
    		this.setGreen(green);
    		this.setPeriod(period);
    		this.setTime(time);    	
    	// det går en period innan vi nollställer time och dt blir grönt...
    	}

    public void step() { 
    	if (this.isGreen()) {
    		this.time++;
    	}
    	else if (this.periodCounter < period) {
    		this.periodCounter++;
    	}
    	else {
    		this.time = 0; this.periodCounter = 0;
    	}
    	// Stegar fram klocka ett steg
    }

    public boolean isGreen() {
    	if (this.time < this.green) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	// Returnerar true om time<green, annars false
    }

    public String  toString()  {
    	return "Period: " + getPeriod() + "'/n' Green: " + getGreen() + 
    			"'\n' Time: " + getTime();
    	}
	
}