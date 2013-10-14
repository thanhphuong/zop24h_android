package vn.fiosoft.zop.data;

import java.util.ArrayList;
import java.util.List;

public class FriendFactory {
	public FriendFactory() {

	}

	public List<Friend> list() {
		List<Friend> friends = new ArrayList<Friend>();

		Friend friend;
		friend = new Friend(1, "Apple1", 10.822515, 106.664428);
		friends.add(friend);
		friend = new Friend(2, "Avocado", 10.768556, 105.335083);
		friends.add(friend);
		friend = new Friend(3, "Banana", 9.633246,105.620727);
		friends.add(friend);
		friend = new Friend(4, "Blueberry", 10.196,107.510376);
		friends.add(friend);		
		

		return friends;
	}
}
