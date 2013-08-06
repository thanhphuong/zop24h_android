package vn.fiosoft.zop;

import java.util.List;

import vn.fiosoft.zop.data.Group;
import vn.fiosoft.zop.data.GroupFactory;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	private List<Group> mGroups;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        
        GroupFactory groupFactory = new GroupFactory();
        mGroups = groupFactory.list();
        setListAdapter(new MainAdapter(this, R.layout.activity_main_list_item, mGroups));
        ListView listView = getListView();
        
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if (mGroups == null)
					return;
				Group group = mGroups.get(position);
				Intent intent = new Intent(MainActivity.this, MapActivity.class);
				intent.putExtra(MapActivity.KEY_LIST_LOCATION, group.getId());
				MainActivity.this.startActivity(intent);
				
			}
		});
    }

}
