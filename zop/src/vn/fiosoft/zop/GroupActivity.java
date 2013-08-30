package vn.fiosoft.zop;

import java.util.List;

import vn.fiosoft.sqlite.FriendDataSource;
import vn.fiosoft.sqlite.GroupDataSource;
import vn.fiosoft.zop.data.Group;
import vn.fiosoft.zop.data.GroupFactory;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GroupActivity extends ListActivity {
	
	private List<Group> mGroups;
	private GroupDataSource datasource;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group); 
        
        datasource = new GroupDataSource(this);
        datasource.open();
        datasource.createGroup("Thanh Phuong");
        datasource.createGroup("Ma Dao");
      
        mGroups = datasource.getAllGroups();
        setListAdapter(new GroupAdapter(this, R.layout.activity_group_list_item, mGroups));
        ListView listView = getListView();
        
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if (mGroups == null)
					return;
				Group group = mGroups.get(position);
				
				
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
