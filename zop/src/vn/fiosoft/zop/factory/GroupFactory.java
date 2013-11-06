package vn.fiosoft.zop.factory;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.sqlite.GroupDataSource;
import vn.fiosoft.zop.data.Group;
import android.content.Context;

public class GroupFactory {
	
	private Context context;
	
	public GroupFactory(Context context) {
		this.context = context;
	}

	/**
	 * 
	 * @param group
	 * @return if successful it return new Group object, otherwise return null.
	 */
	public Group createGroup(Group group) {

		try {
			GroupDataSource datasource = new GroupDataSource(this.context);
			datasource.open();

			Group result = datasource.create(group);

			datasource.close();
			return result;
		} catch (Exception e) {
			return null;
		}

	}

	public void deleteGroup(Group group) {
		try {
			GroupDataSource datasource = new GroupDataSource(this.context);
			datasource.open();

			datasource.delete(group);

			datasource.close();

		} catch (Exception e) {

		}
	}

	
	public List<Group> getAllGroups() {
		List<Group> groups;
		try {
			GroupDataSource datasource = new GroupDataSource(this.context);
			datasource.open();

			groups = datasource.listAll();

			datasource.close();

		} catch (Exception e) {
			groups = new ArrayList<Group>();
		}
		return groups;
	}
}
