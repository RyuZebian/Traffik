package trafficSim;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrafficTest {
	CarPosition D1;
	CarPosition A;
	private CarPosition D2;
	private CarPosition B;
	
	@Test
	public void testCar() {
		
		Car c1 = new Car (0, D1, A) ;
		assertEquals(0, c1.getBornTime());
		assertEquals(D1, c1.getDestination());
		assertEquals(A, c1.getCurrentPosition());	
		}	
	
	@Test
	public void testLane() {
		Car c1 = new Car (0, D1, A);
		Car c2 = new Car (5, D2, B);
		Lane ourLane = new Lane(10);
		ourLane.putLast(c1);
		CarPosition forwardForLast = c1.getCurrentPosition().getForward();
		CarPosition nextToLast = (ourLane.theLane[(ourLane.getLength()-2)]);		
		CarPosition forwardForFifth = (ourLane.theLane[(ourLane.getLength()-5)]).getForward();
		CarPosition sixthCarPos = (ourLane.theLane[(ourLane.getLength()-6)]);
		CarPosition lastInLane = (ourLane.theLane[(ourLane.getLength()-1)]);
		
		//kollar att CarPos har rätt bil som current
		assertEquals(lastInLane.getCurrentCar(), c1);
		
		// kollar att Car har rätt CarPos som current (OBS! CurrentCarPos och CurrentCar oberoende!)
		assertEquals(c1.getCurrentPosition(), lastInLane); 
		
		// kollar att näst sista elementet = forward för sista
		assertEquals(forwardForLast, nextToLast);
		
		// kollar att sjätte elementet = forward för femte
		assertEquals(forwardForFifth, sixthCarPos);
		
		// steppar fram alla bilar (moveForward), just nu bara c1
		ourLane.step();
		
		// stoppar in ny bil c2 sist, där c1 var innan step
		ourLane.putLast(c2);
		
		// flyttar c1 ett steg fram
		//c1.getCurrentPosition().moveForward();
		
		// kollar att car innehåller rätt currentCarPos efter flytt
		assertEquals(c1.getCurrentPosition(), nextToLast);
	
		//kollar att carPos innehåller rätt currentCar efter flytt
		assertEquals(nextToLast.getCurrentCar(), c1);
	
		//kollar att förra carPos inte längre innehåller c1, utan c2
		assertEquals(lastInLane.getCurrentCar(), c2);		
	}

	@Test
	public void testTrafficSystem () {
	TrafficSystem ourTrafficSystem = new TrafficSystem();
	ourTrafficSystem.print();
	ourTrafficSystem.printStatistics();
	}
}
