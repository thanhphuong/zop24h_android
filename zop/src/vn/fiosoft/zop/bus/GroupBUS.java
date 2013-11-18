package vn.fiosoft.zop.bus;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.dao.GroupDAO;
import vn.fiosoft.zop.dto.GroupDTO;
import android.content.Context;

public class GroupBUS {
	
	private Context context;
	
	public GroupBUS(Context context) {
		this.context = context;
	}

	/**
	 * 
	 * @param group
	 * @return if successful it return new Group object, otherwise return null.
	 */
	public GroupDTO create(GroupDTO group) {

		try {
			GroupDAO datasource = new GroupDAO(this.context);
			datasource.open();

			GroupDTO result = datasource.create(group);

			datasource.close();
			return result;
		} catch (Exception e) {
			return null;
		}

	}

	public void delete(GroupDTO group) {
		try {
			GroupDAO datasource = new GroupDAO(this.context);
			datasource.open();

			datasource.delete(group);

			datasource.close();

		} catch (Exception e) {

		}
	}

	
	public List<GroupDTO> listAll() {
		List<GroupDTO> groups;
		try {
			GroupDAO datasource = new GroupDAO(this.context);
			datasource.open();

			groups = datasource.listAll();

			datasource.close();

		} catch (Exception e) {
			groups = new ArrayList<GroupDTO>();
		}
		return groups;
	}
}
