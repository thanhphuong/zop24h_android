package vn.fiosoft.zop;

import java.util.List;

import vn.fiosoft.zop.dto.MenuItemDTO;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MapMenuAdapter extends ArrayAdapter<MenuItemDTO> {

	private Activity mActivity;
	private int mResourceId;

	public MapMenuAdapter(Activity activity, int resourceId, List<MenuItemDTO> objects) {
		super(activity, resourceId, objects);
		this.mActivity = activity;
		this.mResourceId = resourceId;
	}

	private final class ViewHolder {
		public ImageView image;
		public TextView item;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater().inflate(mResourceId,
					parent, false);

			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.menu_image);
			viewHolder.item = (TextView) convertView.findViewById(R.id.menu_item);
			
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		MenuItemDTO menuItem = getItem(position);
		if (menuItem != null){
			viewHolder.item.setText(menuItem.getItem());
			viewHolder.image.setImageResource(menuItem.getImage());
		}

		return convertView;
	}

}
