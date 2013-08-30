package vn.fiosoft.zop;

import java.util.List;

import vn.fiosoft.sqlite.FriendDataSource;
import vn.fiosoft.zop.data.Friend;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FriendActivity extends ListActivity{
	
	private List<Friend> mFriends;
	private FriendDataSource datasource;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend); 
        
        datasource = new FriendDataSource(this);
        datasource.open();
        
        datasource.createFriend("Thanh Phuong");
        datasource.createFriend("Ma Dao");
        
        mFriends = datasource.getAllFriends();
        setListAdapter(new FriendAdapter(this, R.layout.activity_friend_list_item, mFriends));
        ListView listView = getListView();
        
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if (mFriends == null)
					return;
				Friend friend = mFriends.get(position);				
				
			}
		});
    }
    
    @Override
    protected void onResume() {
      datasource.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }
}
