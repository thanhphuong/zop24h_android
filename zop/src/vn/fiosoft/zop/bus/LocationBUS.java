package vn.fiosoft.zop.bus;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.dto.LocationDTO;

public class LocationBUS {
	public LocationBUS(){
		
	}
	
	public List<LocationDTO> list(int id){
		List<LocationDTO> friends = new ArrayList<LocationDTO>();
		
		LocationDTO friend;
		friend = new LocationDTO("ABui Thanh Phuong 1 sdgsdr erter ewtret", 10, 106);		
		friends.add(friend);
		
		friend = new LocationDTO("aBui Thanh Phuong 2", 20, 100);
		friends.add(friend);
		
		friend = new LocationDTO("cBui Thanh Phuong 3", 10, 100);
		friends.add(friend);
		
		friend = new LocationDTO("aBui Thanh Phuong 4", 30, 98);
		friends.add(friend);
		
		friend = new LocationDTO("dBui Thanh Phuong 5", 0, 0);
		friends.add(friend);
		
		
		friends.add(friend);
		
		return friends;
	}
}
