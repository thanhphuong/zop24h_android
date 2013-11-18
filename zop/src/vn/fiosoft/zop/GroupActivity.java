package vn.fiosoft.zop;

import java.util.Calendar;
import java.util.List;

import vn.fiosoft.zop.bus.GroupBUS;
import vn.fiosoft.zop.dto.GroupDTO;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GroupActivity extends ListActivity {
	
	private List<GroupDTO>	groups;
	private GroupBUS	groupFactory;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);

		groupFactory = new GroupBUS(this);

		groupFactory.create(new GroupDTO(0, null, "Thanh Phuong"));
		groupFactory.create(new GroupDTO(0, null, "Thanh Phuong"));		

		groups = groupFactory.listAll();
		setListAdapter(new GroupAdapter(this, R.layout.activity_group_list_item, groups));
		ListView listView = getListView();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (groups == null)
					return;
				GroupDTO group = groups.get(position);
				
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
