package iamus.ips.objectLocation;

import java.util.ArrayList;
import java.util.List;

import iamus.ips.jpa.entity.PlanEntity;

public class Trilateration {
	
	private double calculateBeaconDistance(double rssi) {
		 
	    float txPower = (float) -70.0; // Manufacture set this power in the device
	    if (rssi == 0){
	 
	        return -1.0; // if we cannot determine accuracy, return -1.
	 
	    }
	 
	    double ratio = rssi*1.0 / txPower;
	    if (ratio < 1.0){
	        return Math.pow(ratio,10);
	 
	    }
	    else{
	        double accuracy = (0.89976)*Math.pow(ratio,7.7095) + 0.111;
	        return accuracy;
	    }
	}
	 
	/**
	* It needs distanceA, distanceB, distanceC, pointA1, pointA2, pointB1, pointB2, pointC1, pointC2
	 * @return 
	*/
	 
	private XYCoord getMeetingPoints(double distanceA, double distanceB, double distanceC, double pointA1, double pointA2, double pointB1, double pointB2, double pointC1, double pointC2) {
	 
	    double w,z,x,y,y2;
	    w = distanceA * distanceA - distanceB * distanceB - pointA1 * pointA1 - pointA2* pointA2 + pointB1 * pointB1 + pointB2 * pointB2;
	 
	    z = distanceB * distanceB - distanceC * distanceC - pointB1* pointB1 - pointB2 * pointB2 + pointC1 * pointC1 + pointC2 * pointC2;
	 
	    x = (w * ( pointC2 - pointB2) - z * ( pointB2 - pointA2)) / (2 * (( pointB1 - pointA1) * ( pointC1 - pointB2) - ( pointC1 - pointB1) * ( pointB2 - pointA2)));
	 
	    y = (w - 2 * x * (pointB1 - pointA1)) / (2 * ( pointB2 - pointA2));
	 
	    y2 = (z - 2 * x * ( pointC1 -pointB1)) / (2 * ( pointC1 - pointB2));
	 
	    y = (y + y2) / 2;
	    
	    System.out.println("_____________________________________________");
		System.out.println("Rsult X: "+x);
		System.out.println("Result Y: "+y);
		return new XYCoord(x,y);
	    
	 
	}
	public XYCoord getLocationByTrilateration(List<XYCoord> coordList, List<Double> rssiList, PlanEntity plan){
		
		List<Double> distanceInMetersList = new ArrayList<>();
		for (double rssi: rssiList) {
			double distance = calculateBeaconDistance(rssi);
			distanceInMetersList.add(distance);
		}
		System.out.println("_____________________________________________");
		System.out.println("Rssi1: "+rssiList.get(0));
		System.out.println("Rssi2: "+rssiList.get(1));
		System.out.println("Rssi3: "+rssiList.get(2));
		
		double distanceInPx1 = distanceInMetersList.get(0)*100/plan.getPlanScale();
		double distanceInPx2 = distanceInMetersList.get(1)*100/plan.getPlanScale();
		double distanceInPx3 = distanceInMetersList.get(2)*100/plan.getPlanScale();
	
		System.out.println("_____________________________________________");
		System.out.println("Distance1: "+distanceInPx1);
		System.out.println("Distance2: "+distanceInPx2);
		System.out.println("Distance3: "+distanceInPx3);
		System.out.println("Distance1: "+distanceInMetersList.get(0));
		System.out.println("Distance2: "+distanceInMetersList.get(1));
		System.out.println("Distance3: "+distanceInMetersList.get(2));
//		
		XYCoord meetingPoint = getMeetingPoints(distanceInPx1, distanceInPx2, distanceInPx3, coordList.get(0).getX(), coordList.get(0).getY(), coordList.get(1).getX(), coordList.get(1).getY() , coordList.get(2).getX(), coordList.get(2).getY());
		
		
//		Getting the closest transmitter and adjusting trilateration result 
		double closestDistance = distanceInMetersList.get(0) ;
		int closestId = 0;
		for (int i = 0; distanceInMetersList.size()> i; i++) {
			if (closestDistance >= distanceInMetersList.get(i)) {
				closestDistance = distanceInMetersList.get(i);
				closestId = i;
			}
		}
		
		if (meetingPoint.getX()<0 || meetingPoint.getX()>plan.getPlanWidth() || !Double.isFinite( meetingPoint.getX())) {
			meetingPoint.setX(coordList.get(closestId).getX());
		}
		if (meetingPoint.getY()<0 || meetingPoint.getY()>plan.getPlanHeight() || !Double.isFinite( meetingPoint.getY())) {
			meetingPoint.setY(coordList.get(closestId).getY());
		}
		
		if(closestDistance < 0.25) {
			meetingPoint.setX(coordList.get(closestId).getX());
			meetingPoint.setY(coordList.get(closestId).getY());
			
		}else if(closestDistance < 1) {
			double middlePoint = (meetingPoint.getX() + coordList.get(closestId).getX())/2;
			meetingPoint.setX(middlePoint);
			middlePoint = (meetingPoint.getY() + coordList.get(closestId).getY())/2;
			meetingPoint.setY(middlePoint);
		}
			

		
		
		//		
//		

//		//Distance = 10 ^ ((Measured Power � RSSI)/(10 * N))
//		
//		double distance1 = Math.pow(10d, ((double) txPower - rssi1) / (10 * 2));
//		double distance2 = Math.pow(10d, ((double) txPower - rssi2) / (10 * 2));
//		double distance3 = Math.pow(10d, ((double) txPower - rssi3) / (10 * 2));
	
		
	    //DECLARE VARIABLES

//	    double[] P1   = new double[2];
//	    double[] P2   = new double[2];
//	    double[] P3   = new double[2];
//	    double[] ex   = new double[2];
//	    double[] ey   = new double[2];
//	    double[] p3p1 = new double[2];
//	    double jval  = 0;
//	    double temp  = 0;
//	    double ival  = 0;
//	    double p3p1i = 0;
//	    double triptx = 0;
//	    double tripty = 0;
//	    double xval;
//	    double yval;
//	    double t1;
//	    double t2;
//	    double t3;
//	    double t;
//	    double exx;
//	    double d;
//	    double eyy;
//
//	    //TRANSALTE POINTS TO VECTORS
//	    //POINT 1
//	    P1[0] = location1.getLatitude();
//	    P1[1] = location1.getLongitude();
//	    //POINT 2
//	    P2[0] = location2.getLatitude();
//	    P2[1] = location2.getLongitude();
//	    //POINT 3
//	    P3[0] = location3.getLatitude();
//	    P3[1] = location3.getLongitude();
//	    
//
//	    for (int i = 0; i < P1.length; i++) {
//	        t1   = P2[i];
//	        t2   = P1[i];
//	        t    = t1 - t2;
//	        temp += (t*t);
//	    }
//	    d = Math.sqrt(temp);
//	    for (int i = 0; i < P1.length; i++) {
//	        t1    = P2[i];
//	        t2    = P1[i];
//	        exx   = (t1 - t2)/(Math.sqrt(temp));
//	        ex[i] = exx;
//	    }
//	    for (int i = 0; i < P3.length; i++) {
//	        t1      = P3[i];
//	        t2      = P1[i];
//	        t3      = t1 - t2;
//	        p3p1[i] = t3;
//	    }
//	    for (int i = 0; i < ex.length; i++) {
//	        t1 = ex[i];
//	        t2 = p3p1[i];
//	        ival += (t1*t2);
//	    }
//	    for (int  i = 0; i < P3.length; i++) {
//	        t1 = P3[i];
//	        t2 = P1[i];
//	        t3 = ex[i] * ival;
//	        t  = t1 - t2 -t3;
//	        p3p1i += (t*t);
//	    }
//	    for (int i = 0; i < P3.length; i++) {
//	        t1 = P3[i];
//	        t2 = P1[i];
//	        t3 = ex[i] * ival;
//	        eyy = (t1 - t2 - t3)/Math.sqrt(p3p1i);
//	        ey[i] = eyy;
//	    }
//	    for (int i = 0; i < ey.length; i++) {
//	        t1 = ey[i];
//	        t2 = p3p1[i];
//	        jval += (t1*t2);
//	    }
//	    xval = (Math.pow(distance1, 2) - Math.pow(distance2, 2) + Math.pow(d, 2))/(2*d);
//	    yval = ((Math.pow(distance1, 2) - Math.pow(distance3, 2) + Math.pow(ival, 2) + Math.pow(jval, 2))/(2*jval)) - ((ival/jval)*xval);
//
//	    t1 = location1.getLatitude();
//	    t2 = ex[0] * xval;
//	    t3 = ey[0] * yval;
//	    triptx = t1 + t2 + t3;
//
//	    t1 = location1.getLongitude();
//	    t2 = ex[1] * xval;
//	    t3 = ey[1] * yval;
//	    tripty = t1 + t2 + t3;
//		System.out.println("_____________________________________________");
//		System.out.println("result X: "+triptx);
//		System.out.println("_____________________________________________");
//		System.out.println("result Y: "+tripty);


	    return new XYCoord(meetingPoint.getX(), meetingPoint.getY());
    
}

	
}
