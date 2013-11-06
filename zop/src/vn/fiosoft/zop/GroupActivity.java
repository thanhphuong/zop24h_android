package vn.fiosoft.zop;

import java.util.Calendar;
import java.util.List;

import vn.fiosoft.zop.data.Group;
import vn.fiosoft.zop.factory.GroupFactory;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GroupActivity extends ListActivity {
	
	private List<Group>	groups;
	private GroupFactory	groupFactory;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);

		groupFactory = new GroupFactory(this);

		groupFactory.createGroup(new Group(0, null, "Thanh Phuong"));
		groupFactory.createGroup(new Group(0, null, "Thanh Phuong"));		

		groups = groupFactory.getAllGroups();
		setListAdapter(new GroupAdapter(this, R.layout.activity_group_list_item, groups));
		ListView listView = getListView();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (groups == null)
					return;
				Group group = groups.get(position);
				
				Bundle extras = new Bundle();
				extras.putSerializable("result", group);
				
				Intent returnIntent = new Intent();
				returnIntent.putExtras(extras);
				setResult(RESULT_OK, returnIntent);
				finish();

			}
		});
	}
}
