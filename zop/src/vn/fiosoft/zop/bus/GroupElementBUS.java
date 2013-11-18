package vn.fiosoft.zop.bus;

import java.util.ArrayList;
import java.util.List;
import vn.fiosoft.zop.dao.GroupElementDAO;
import vn.fiosoft.zop.dto.GroupDTO;
import vn.fiosoft.zop.dto.GroupElementDTO;
import android.content.Context;

public class GroupElementBUS {

	private Context	context;

	public GroupElementBUS(Context context) {
		this.context = context;
	}

	/**
	 * 
	 * @param object
	 * @return if successful it return new Group object, otherwise return null.
	 */
	public GroupElementDTO create(GroupElementDTO object) {

		try {
			GroupElementDAO datasource = new GroupElementDAO(this.context);
			datasource.open();

			GroupElementDTO result = datasource.create(object);

			datasource.close();
			return result;
		} catch (Exception e) {
			return null;
		}

	}

	public void delete(GroupElementDTO object) {
		try {
			GroupElementDAO datasource = new GroupElementDAO(this.context);
			datasource.open();

			datasource.delete(object);

			datasource.close();

		} catch (Exception e) {

		}
	}

	public List<GroupElementDTO> listAllElementOfGroup(GroupDTO object) {
		List<GroupElementDTO> groups;
		try {
			GroupElementDAO datasource = new GroupElementDAO(this.context);
			datasource.open();

			groups = datasource.listAllElementOfGroup(object);

			datasource.close();

		} catch (Exception e) {
			groups = new ArrayList<GroupElementDTO>();
		}
		return groups;
	}
}
