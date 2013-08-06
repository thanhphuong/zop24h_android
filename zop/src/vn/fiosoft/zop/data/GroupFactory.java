package vn.fiosoft.zop.data;

import java.util.ArrayList;
import java.util.List;

public class GroupFactory {
	public GroupFactory() {

	}

	public List<Group> list() {
		List<Group> friends = new ArrayList<Group>();

		Group friend;
		friend = new Group(1, "Apple1");
		friends.add(friend);
		friend = new Group(2, "Avocado");
		friends.add(friend);
		friend = new Group(3, "Banana");
		friends.add(friend);
		friend = new Group(4, "Blueberry");
		friends.add(friend);
		friend = new Group(5, "Coconut");
		friends.add(friend);
		friend = new Group(6, "Durian");
		friends.add(friend);
		friend = new Group(7, "Guava");
		friends.add(friend);
		friend = new Group(8, "Kiwifruit");
		friends.add(friend);
		friend = new Group(9, "Jackfruit");
		friends.add(friend);
		friend = new Group(10, "Mango");
		friends.add(friend);
		friend = new Group(11, "Olive");
		friends.add(friend);
		

		return friends;
	}
}
