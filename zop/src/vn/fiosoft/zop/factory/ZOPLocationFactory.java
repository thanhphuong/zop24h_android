package vn.fiosoft.zop.factory;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.data.ZOPLocation;

public class ZOPLocationFactory {
	public ZOPLocationFactory(){
		
	}
	
	public List<ZOPLocation> list(int id){
		List<ZOPLocation> friends = new ArrayList<ZOPLocation>();
		
		ZOPLocation friend;
		friend = new ZOPLocation("ABui Thanh Phuong 1 sdgsdr erter ewtret", 10, 106);		
		friends.add(friend);
		
		friend = new ZOPLocation("aBui Thanh Phuong 2", 20, 100);
		friends.add(friend);
		
		friend = new ZOPLocation("cBui Thanh Phuong 3", 10, 100);
		friends.add(friend);
		
		friend = new ZOPLocation("aBui Thanh Phuong 4", 30, 98);
		friends.add(friend);
		
		friend = new ZOPLocation("dBui Thanh Phuong 5", 0, 0);
		friends.add(friend);
		
		
		friends.add(friend);
		
		return friends;
	}
}
