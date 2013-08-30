package vn.fiosoft.zop;

import java.util.List;

import vn.fiosoft.zop.data.Friend;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FriendAdapter extends ArrayAdapter<Friend> {

	private Activity mActivity;
	private int mResourceId;

	public FriendAdapter(Activity activity, int resourceId, List<Friend> objects) {
		super(activity, resourceId, objects);
		this.mActivity = activity;
		this.mResourceId = resourceId;
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
		
		Friend friend = getItem(position);
		if (friend != null){
			viewHolder.name.setText(friend.getName());
		}

		return convertView;
	}

}
