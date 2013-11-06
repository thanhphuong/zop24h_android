package vn.fiosoft.zop.factory;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.sqlite.FriendDataSource;
import vn.fiosoft.zop.data.Friend;
import android.content.Context;

public class FriendFactory {

	private Context context;

	public FriendFactory(Context context) {
		this.context = context;
	}

	/**
	 * 
	 * @param friend
	 * @return if successful it return new Friend object, otherwise return null.
	 */
	public Friend createFriend(Friend friend) {

		try {
			FriendDataSource datasource = new FriendDataSource(this.context);
			datasource.open();

			Friend result = datasource.create(friend);

			datasource.close();
			return result;
		} catch (Exception e) {
			return null;
		}

	}

	public void deleteFriend(Friend friend) {
		try {
			FriendDataSource datasource = new FriendDataSource(this.context);
			datasource.open();

			datasource.delete(friend);

			datasource.close();

		} catch (Exception e) {

		}
	}

	
	public List<Friend> getAllFriends() {
		List<Friend> friends;
		try {
			FriendDataSource datasource = new FriendDataSource(this.context);
			datasource.open();

			friends = datasource.listAll();

			datasource.close();

		} catch (Exception e) {
			friends = new ArrayList<Friend>();
		}
		return friends;
	}
}
