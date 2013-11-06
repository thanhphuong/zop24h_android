package vn.fiosoft.zop;

import java.util.Calendar;
import java.util.List;

import vn.fiosoft.sqlite.FriendDataSource;
import vn.fiosoft.zop.data.Friend;
import vn.fiosoft.zop.factory.FriendFactory;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FriendActivity extends ListActivity {

	private List<Friend>	friends;
	private FriendFactory	friendFactory;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);

		friendFactory = new FriendFactory(this);

		friendFactory.createFriend(new Friend(0, Calendar.getInstance().getTime(), null, "Thanh Phuong", 10, 106));
		friendFactory.createFriend(new Friend(0, Calendar.getInstance().getTime(),  null, "Thanh Phuong", 10, 106.5));

		friends = friendFactory.getAllFriends();
		setListAdapter(new FriendAdapter(this, R.layout.activity_friend_list_item, friends));
		ListView listView = getListView();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (friends == null)
					return;
				Friend friend = friends.get(position);
				
				Bundle extras = new Bundle();
				extras.putSerializable("result", friend);
				
				Intent returnIntent = new Intent();
				returnIntent.putExtras(extras);
				setResult(RESULT_OK, returnIntent);
				finish();

			}
		});
	}

}
