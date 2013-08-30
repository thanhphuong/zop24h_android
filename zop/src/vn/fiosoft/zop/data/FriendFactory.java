package vn.fiosoft.zop.data;

import java.util.ArrayList;
import java.util.List;

public class FriendFactory {
	public FriendFactory() {

	}

	public List<Friend> list() {
		List<Friend> friends = new ArrayList<Friend>();

		Friend friend;
		friend = new Friend(1, "Apple1");
		friends.add(friend);
		friend = new Friend(2, "Avocado");
		friends.add(friend);
		friend = new Friend(3, "Banana");
		friends.add(friend);
		friend = new Friend(4, "Blueberry");
		friends.add(friend);
		friend = new Friend(5, "Coconut");
		friends.add(friend);
		friend = new Friend(6, "Durian");
		friends.add(friend);
		friend = new Friend(7, "Guava");
		friends.add(friend);
		friend = new Friend(8, "Kiwifruit");
		friends.add(friend);
		friend = new Friend(9, "Jackfruit");
		friends.add(friend);
		friend = new Friend(10, "Mango");
		friends.add(friend);
		friend = new Friend(11, "Olive");
		friends.add(friend);
		

		return friends;
	}
}
