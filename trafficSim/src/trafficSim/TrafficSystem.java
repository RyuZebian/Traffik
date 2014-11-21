package trafficSim;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TrafficSystem {
    // Definierar de v�gar och signaler som ing�r i det
    // system som skall studeras.
    // Samlar statistik
    
	
    // Attribut som beskriver best�ndsdelarna i systemet
	static int r0Length;
	static int r1Length;
	static int r2Length;
	static int greenLength1;
	static int periodLength1;
	static int greenLength2;
	static int periodLength2;
	static private int noOfSteps;
	static int arrivalIntensity;
	static int denominatorForD1;
	static int destinationCounter = 0;
	static CarPosition D1;
	static CarPosition D2;
	static int deniedCars = 0;
	static int timeTakenForAll = 0;
	static int longestTime = 0;
	static int averageTime = 0;
	static int numberOfCarsPassed = 0;
	static int carsThatWentToD1 = 0;
	static int carsThatWentToD2 = 0;
	
	private Lane  r0; 
    private Lane  r1;
    private Lane  r2; 
    private Light s1; 
    private Light s2;
   
    
    
   
   static boolean arrivalTimeCalc (int time) {
	   if (time % arrivalIntensity == 0) {
		   return true;
	   }
	   else {
		   return false;
	   }
   }
    
    static boolean destinationIsD1 () {
    	destinationCounter++;
    	if (destinationCounter % denominatorForD1 == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public Properties loadProp() { 
    Properties properties = new Properties();
    InputStream ourStream = getClass().getResourceAsStream("properties.properties");
    try {
    	properties.load(ourStream); 
    } catch (IOException e){
    	e.printStackTrace();
    }
   return properties;
    }
   // new FileInputStream("/trafficSim/src/trafficSim/properties"); };
    //properties.load(new FileInputStream("/trafficSim/src/trafficSim/properties"));
    //properties.
    // Diverse attribut f�r simuleringsparametrar (ankomstintensiteter,
    // destinationer...)

    // Diverse attribut f�r statistiksamling
    //....    
    
    private int time = 0;
    
    
    //''''''''''''''''''''''''''''''''''''''''''''
    //bytte r0 till "package" för testning. BYT TILLBAKS.
    //''''''''''''''''
    
    public TrafficSystem() {
    readParameters();
    r0 = new Lane (r0Length); 
    r1 = new Lane (r1Length); 
    r2 = new Lane (r2Length);
    s1 = new Light(periodLength1, greenLength1, 0);
    s2 = new Light(periodLength2, greenLength2, 0);
 	D1 = new CarPosition();
 	D2 = new CarPosition();
    D1.setIdNumber(-1);
    D2.setIdNumber(-2);
    deniedCars = 0;
 	while (time <= noOfSteps+1) {
    	step();
    	time++;
    }
    }
    
    
    public void readParameters() {
	TrafficSystem.r0Length = Integer.parseInt(this.loadProp().getProperty("LaneR0Length"));
	TrafficSystem.r1Length = Integer.parseInt(this.loadProp().getProperty("LaneR1Length"));
	TrafficSystem.r2Length = Integer.parseInt(this.loadProp().getProperty("LaneR2Length"));	
    	
	TrafficSystem.greenLength1 = Integer.parseInt(this.loadProp().getProperty("s1Green"));
	TrafficSystem.periodLength1 = Integer.parseInt(this.loadProp().getProperty("s1Period"));
	TrafficSystem.greenLength2 = Integer.parseInt(this.loadProp().getProperty("s2Green"));
	TrafficSystem.periodLength2 = Integer.parseInt(this.loadProp().getProperty("s2Period"));
	TrafficSystem.noOfSteps = Integer.parseInt(this.loadProp().getProperty("noOfSteps"));
	TrafficSystem.arrivalIntensity = Integer.parseInt(this.loadProp().getProperty("arrivalIntensity"));
	TrafficSystem.denominatorForD1 = Integer.parseInt(this.loadProp().getProperty("denominatorForD1"));

    // L�ser in parametrar f�r simuleringen
	// Metoden kan l�sa fr�n terminalf�nster, dialogrutor
	// eller fr�n en parameterfil. Det sista alternativet
	// �r att f�redra vid uttestning av programmet eftersom
	// man inte d� beh�ver mata in v�rdena vid varje k�rning.
    // Standardklassen Properties �r anv�ndbar f�r detta. 
    }

    public void step() {
	s1.step();
	s2.step();
	if (s1.isGreen() && r1.firstCar() != null) {
		calcCarTimeStats(r1);
		carsThatWentToD1++;
		r1.getFirst();
	}
	
	if (s2.isGreen() && r2.firstCar() != null) {
		calcCarTimeStats(r2);
		carsThatWentToD2++;
		r2.getFirst();
	}
	r1.step();
	r2.step();
		
	if (r0.firstCar() != null) {
		if (r0.firstCar().getDestination().getIdNumber() == -1 && r1.lastFree()) {
			r1.putLast(r0.getFirst());
		}
		else if (r0.firstCar().getDestination().getIdNumber() == -2 && r2.lastFree()) {
			r2.putLast(r0.getFirst());
		}	
	}
	
	r0.step();
	
	if (arrivalTimeCalc(time) && r0.lastFree()) {
		if (destinationIsD1()) {	
			Car newCar = new Car (time, D1, null);
			r0.putLast(newCar);
		}
		else {
			Car newCar = new Car (time, D2, null);
			r0.putLast(newCar);
		}	
	}		
	else if (arrivalTimeCalc (time) && r0.lastFree() == false) {
		deniedCars++;
	}
	}
    // Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, l�gg in och ta ur p� de olika Lane-kompenenterna
    
    public void calcCarTimeStats (Lane l) {
    	numberOfCarsPassed++;
    	int timeTaken = time - l.firstCar().getBornTime() -1;
    	timeTakenForAll = timeTakenForAll + timeTaken; 
    	averageTime = timeTakenForAll/numberOfCarsPassed;
    	if ( timeTaken > longestTime) {
    		longestTime = timeTaken;
    	}
    	
    }
    
    public void printStatistics() {
    	System.out.println("Lane r0 : "+ r0.printStatText());
    	System.out.println("Lane r1 : "+ r1.printStatText());
    	System.out.println("Lane r2 : "+ r2.printStatText());
    	System.out.println("Number of cars passed: " + numberOfCarsPassed);
    	System.out.println("Cars that went to D1: " + carsThatWentToD1);
    	System.out.println("Cars that went to D2: " + carsThatWentToD2);
    	System.out.println("Number of cars denied: " + deniedCars);
    	System.out.println("Longest time taken: " + longestTime);
    	System.out.println("Average time taken: " + averageTime);
    	System.out.println(s1.toString());
    	System.out.println(s2.toString());
    	// Skriv statistiken samlad s� h�r l�ngt
    }

    public void print() {
    	System.out.println(r1.toString() + "|<-R1| " + r0.toString() + "|<-R0|");
    	System.out.println(r2.toString() + "|<-R2|");
    	//System.out.println(""+ (r0.theLane[0].getForward().getCurrentCar()));
    	// Skriv ut en grafisk representation av k�situationen
    	// med hj�lp av klassernas toString-metoder
    }

}