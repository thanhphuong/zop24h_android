package vn.fiosoft.zop;

import java.util.List;

import vn.fiosoft.zop.data.Friend;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FriendAdapter extends ArrayAdapter<Friend> {
	
	private Activity mActivity;
	private int mLayoutId;
	
	public final class ViewHolder{
		TextView mName;
	}
	
	public FriendAdapter(Activity activity, int layoutId, List<Friend> objects) {
		super(activity, layoutId, objects);
		this.mActivity = activity;
		this.mLayoutId = layoutId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final Friend friend = getItem(position);
		if(friend == null)
			return null;
		
		ViewHolder viewHolder;
		if (convertView == null){
			convertView = mActivity.getLayoutInflater().inflate(mLayoutId, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.mName = (TextView) convertView.findViewById(R.id.name);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		return convertView;
	}

}
