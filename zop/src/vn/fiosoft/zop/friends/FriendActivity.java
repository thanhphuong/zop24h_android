package vn.fiosoft.zop.friends;

import java.util.List;

import vn.fiosoft.zop.R;
import vn.fiosoft.zop.bus.FriendBUS;
import vn.fiosoft.zop.dto.FriendDTO;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FriendActivity extends ListActivity implements OnClickListener {

	private List<FriendDTO>	friends;
	private FriendBUS	friendFactory;
	
	private View buttonAddNewFriend;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);

		buttonAddNewFriend = findViewById(R.id.add_new_friend);
		
		buttonAddNewFriend.setOnClickListener(this);
		
		friendFactory = new FriendBUS(this);
		friends = friendFactory.listAll();
		setListAdapter(new FriendAdapter(this, R.layout.activity_friend_list_item, friends));
		ListView listView = getListView();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (friends == null)
					return;
				FriendDTO friend = friends.get(position);
				
				Bundle extras = new Bundle();
				extras.putSerializable("result", friend);
				
				Intent returnIntent = new Intent();
				returnIntent.putExtras(extras);
				setResult(RESULT_OK, returnIntent);
				finish();

			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v == buttonAddNewFriend){
			startActivity(new Intent(this, FriendAddActivity.class));
		}
		
	}

}
