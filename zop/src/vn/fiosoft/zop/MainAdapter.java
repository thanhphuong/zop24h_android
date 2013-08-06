package vn.fiosoft.zop;

import java.util.List;

import vn.fiosoft.zop.data.Group;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<Group> {

	private Activity mActivity;
	private int mResourceId;

	public MainAdapter(Activity activity, int resourceId, List<Group> objects) {
		super(activity, resourceId, objects);
		mActivity = activity;
		mResourceId = resourceId;
	}

	private final class ViewHolder {
		public TextView name;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater().inflate(mResourceId,
					parent, false);

			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Group group = getItem(position);
		if (group != null){
			viewHolder.name.setText(group.getName());
		}

		return convertView;
	}

}
