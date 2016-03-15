package bbs.myother;

import java.util.Comparator;

import bbs.database.hibernate.Replyinfo;
 

public class Cmp implements Comparator{
	
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Replyinfo r1 = (Replyinfo) o1;
		Replyinfo r2 = (Replyinfo) o2;
		return r1.getFloorNumber().compareTo(r2.getFloorNumber());
	}

}
