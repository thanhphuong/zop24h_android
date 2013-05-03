package vn.fiosoft.zop.data;

import java.util.ArrayList;
import java.util.List;

public class FriendFactory {
	
	public FriendFactory(){
		
	}
	
	public List<Friend> list(){
		List<Friend> friends = new ArrayList<Friend>();
		
		Friend friend;
		friend = new Friend("Bui Thanh Phuong 1", 10, 106);		
		friends.add(friend);
		
		friend = new Friend("Bui Thanh Phuong 2", 10, 106);
		friends.add(friend);
		
		friend = new Friend("Bui Thanh Phuong 3", 10, 106);
		friends.add(friend);
		
		friend = new Friend("Bui Thanh Phuong 4", 10, 106);
		friends.add(friend);
		
		friend = new Friend("Bui Thanh Phuong 5", 10, 106);
		friends.add(friend);
		
		return friends;
	}

}
