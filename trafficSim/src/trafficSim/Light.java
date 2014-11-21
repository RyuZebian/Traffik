package trafficSim;
/**
 * Light är en klass som beter sig som ett trafikljus
 * 
 */
public class Light {
    
	/**
     * period är antalet steg som man väntar innan det blir grönt igen. 
     * periodCounter räknar upp mot period.
     */
	private int period;
    
	public int periodCounter = 0;
    
    public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	private int time;  // Intern klocka: 0, 1, ... period-1, 0, 1 ...
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	private int green; // Signalen gr�n n�r time<green 
	
	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	/**
	 * Light är konstruktor för klassen Light
	 * @param period hur länge vi väntar innan grönljus
	 * @param green hur länge grönljus varar
	 * @param time starttiden
	 */
    public Light(int period, int green, int time) {
    		this.setGreen(green);
    		this.setPeriod(period);
    		this.setTime(time);    	
    	}

    /**
     * step inkrementerar time om det är grönt, annars periodCounter. 
     * När periodCounter == period nollställs både time och periodCounter.
     * Det blir grönt igen.
     */
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
    }

    /**
     * isGreen returnerar true om time < green, annars false.
     */
    public boolean isGreen() {
    	if (this.time < this.green) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * toString konverterar period, green och time till strängar.
     */
    public String  toString()  {
    	return "Period: " + getPeriod() + "'/n' Green: " + getGreen() + 
    			"'\n' Time: " + getTime();
    	}
}